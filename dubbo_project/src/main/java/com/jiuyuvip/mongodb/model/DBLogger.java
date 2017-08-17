package com.jiuyuvip.mongodb.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


/**
* DBLogger
 */
@Document(collection = "logger")
public class DBLogger   {

	@Id
	private String id;//唯一标识id：时间戳+随机的6位字符串

	private String path;//路径

	private String request_parameter;//请求参数

	private String result_parameter;//返回结果

	private String errorType;//错误类型

	private String errorContent;//错误内容

	private String use_millisecinds;//间隔时间

	private String createtime;//发生时间：年月日时分秒

	public String getErrorContent() {
		return errorContent;
	}

	public void setErrorContent(String errorContent) {
		this.errorContent = errorContent;
	}

	public String getErrorType() {
		return errorType;
	}

	public void setErrorType(String errorType) {
		this.errorType = errorType;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public String getUse_millisecinds() {

		return use_millisecinds;
	}

	public void setUse_millisecinds(String use_millisecinds) {
		this.use_millisecinds = use_millisecinds;
	}

	public String getResult_parameter() {

		return result_parameter;
	}

	public void setResult_parameter(String result_parameter) {
		this.result_parameter = result_parameter;
	}

	public String getPath() {

		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getRequest_parameter() {

		return request_parameter;
	}

	public void setRequest_parameter(String request_parameter) {
		this.request_parameter = request_parameter;
	}

	public String getId() {

		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}