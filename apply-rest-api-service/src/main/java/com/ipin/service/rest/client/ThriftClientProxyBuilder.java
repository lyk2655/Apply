package com.ipin.service.rest.client;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.thrift.TServiceClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ipin.service.rest.constants.Constants;

import javassist.CannotCompileException;
import javassist.ClassClassPath;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.NotFoundException;

/**
 * ThriftClientProxyBuilder
 * ThriftClientProxyBuilder代理生成
 * @author zhongyongsheng
 *
 */
public class ThriftClientProxyBuilder {

	private static final Logger logger = LoggerFactory.getLogger(ThriftClientProxyBuilder.class);
	
	/**
	 * TServiceClient缓存
	 */
	private static Map<String, Object> tServiceClientProxyCache = new HashMap<String, Object>();

	/**
	 * 建造ThriftClient代理
	 * @param ip ip地址
	 * @param port 端口号
	 * @param ifaceFullName 接口全名
	 * @return
	 */
	public static synchronized Object buildThriftClientProxy(String ip, int port, String ifaceFullName) {
		if(StringUtils.isBlank(ip) || port <= 0 || StringUtils.isBlank(ifaceFullName)) {
			throw new IllegalArgumentException("Building thrift client proxy, Argument is illegal!");
		}
		
		Class<?> ifaceClazz = null;
		try {
			ifaceClazz = Class.forName(ifaceFullName);
		} catch (ClassNotFoundException e) {
			throw new IllegalArgumentException("Building thrift client proxy, ifaceFullName argument is illegal!");
		}
		
		try { 
			String key = ip + "_" + port + "_" + ifaceFullName;
			Object tServiceClient = tServiceClientProxyCache.get(key);
			if(tServiceClient == null) {
				// 创建targetClass实现类
				//ClassPool：CtClass对象的容器  
		        ClassPool pool = ClassPool.getDefault();  
		        // 解决web路径下自定类加载器的搜索路径
		        pool.insertClassPath(new ClassClassPath((ThriftClientProxyBuilder.class)));
		          
		        // 代理类名字
		        CtClass proxyClass = pool.makeClass(ifaceClazz.getCanonicalName() + "_proxy");  
		        
		        // 继承接口 
		        CtClass targetInterfaceClass=pool.get(ifaceClazz.getName());  
		        proxyClass.addInterface(targetInterfaceClass);
		        
		        // 实现接口
				CtMethod[] methods = targetInterfaceClass.getDeclaredMethods();
				for (int n = 0; n < methods.length; n++) {
					CtMethod m = methods[n];
					generateProxyMethods(pool, proxyClass, m, ifaceClazz, ip, port);
				}
		        
		        // 生成类和实例，并缓存
		        Class<?> clazz = proxyClass.toClass(); 
		        tServiceClientProxyCache.put(key, clazz.newInstance());
		        tServiceClient = tServiceClientProxyCache.get(key);
			}
			
			return tServiceClient;
		} catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Building thrift client proxy error!");
		}
		
	}
	
	/**
	 * 创建具体的代理方法内容
	 * @param ip
	 * @param port
	 * @param method
	 * @param targetClazz
	 * @return
	 * @throws NotFoundException
	 */
	private static String createMethodBody(String ip, int port, CtMethod method, Class<?> targetClazz) throws NotFoundException {
		String ifaceName = targetClazz.getCanonicalName();
		int lastIndex = ifaceName.lastIndexOf(".");
		String serviceName = ifaceName.substring(0, lastIndex);
		// 构造方法体
		StringBuilder mbody = new StringBuilder();
		mbody.append("{\n")
					  .append("    String ip = \"" + ip + "\";\n")
					  .append("    int port = " + port + ";\n")
					  .append("    com.ipin.service.rest.client.ThriftClient thriftClient = new com.ipin.service.rest.client.impl.ThriftClientImpl(ip, port);\n")
					  .append("    org.apache.thrift.transport.TTransport transport = thriftClient.getTransport(" + Constants.THRIFT_CLIENT_PROXY_TRANSPORT_TIMEOUT + ");\n")
					  .append("    org.apache.thrift.protocol.TProtocol protocol = new org.apache.thrift.protocol.TBinaryProtocol(transport);\n")
					  .append("    Throwable throwException = null;\n")
					  .append("    org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(getClass());\n")
					  .append("    try { \n")
					  .append("        transport.open();\n")
					  .append("        " + ifaceName + " client = new " + serviceName + ".Client(protocol);\n");
		if (method.getReturnType().getName().equals("void")) {
		mbody.append("        client." + method.getName() + "($$);\n");
		} else {
		mbody.append("        return client." + method.getName() + "($$);\n");
		}
		mbody.append("    } catch (Throwable e) { \n");
		mbody.append("       if (e instanceof org.apache.thrift.TApplicationException && ((org.apache.thrift.TApplicationException) e).getType() == org.apache.thrift.TApplicationException.MISSING_RESULT) {\n"); // 结果为null
		if (!method.getReturnType().getName().equals("void")) {
		mbody.append("            return null;\n");
		}
		else {
		mbody.append("            ;\n");
		}
		mbody.append("        }\n")
					  .append("        throw e;\n")
					  .append("    } finally { \n")
			          .append("        if (transport != null) { \n")
			          .append("           transport.close(); \n")
			          .append("        } \n")
			          .append("    } \n")
			          .append("}");
		return mbody.toString();
	}
	
	/**
	 * 创建代理方法
	 * @param pool
	 * @param proxy
	 * @param method
	 * @param targetClass
	 * @param ip
	 * @param port
	 */
	private static void generateProxyMethods(ClassPool pool, CtClass proxy, CtMethod method, Class<?> targetClass, String ip, int port) {
		try {
			CtMethod cm = new CtMethod(method.getReturnType(), method.getName(), method.getParameterTypes(), proxy);
			cm.setExceptionTypes(method.getExceptionTypes());
			cm.setBody(createMethodBody(ip, port, method, targetClass));
			proxy.addMethod(cm);
		} catch (CannotCompileException e) {
			logger.error("Error occured while generating methods, message code : " + e.getMessage(), e);
		} catch (NotFoundException e) {
			logger.error("Error occured while generating methods, message code : " + e.getMessage(), e);
		}
	}
	
	public static void main(String[] args ) {
		ThriftClientProxyBuilder.buildThriftClientProxy("101.1.1.1", 744, "com.ipin.thrift.edu.EduInfoService$Iface");
	}
}
