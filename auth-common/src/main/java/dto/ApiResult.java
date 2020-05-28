package dto;

//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;
import eumn.CommonApiStatusCode;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * 接口统一返回类
 *@author  wangxulong duanxian0402@126.com
 *@version 20191130000
 */
@Data
@NoArgsConstructor
//@ApiModel("返回对象")
public class ApiResult<T> {
    /* 状态码 */
//    @ApiModelProperty("状态码")
    private int code;  // 状态码
//    @ApiModelProperty("返回消息")
    /* 返回消息 */
    private String message; // 信息
    /* 返回数据 */
//    @ApiModelProperty("返回数据")
    private T data; // 数据


    /*
     *初始化构造对象
     *@return com.tontron.expo.common.dto.ApiResult
     *
     *@author wangxulong duanxian0402@126.com
     *@version 20191130000
     *
     **/
    private static <T> ApiResult<T> init() {
        return new ApiResult<T>();
    }

    /*
     * 成功 携带自定义数据返回

     *@param data
     *@return com.tontron.expo.common.dto.ApiResult<T>
     *
     *@author wangxulong duanxian0402@126.com
     *@version 20191130000
     *
     **/
    public static <T> ApiResult<T> success(T data) {
        ApiResult<T> result = init();
        result.setCode(CommonApiStatusCode.SUCCESS.getCode());
        result.setMessage(CommonApiStatusCode.SUCCESS.getText());
        result.setData(data);
        return result;
    }

    /*
     * 成功返回

     *@param message 自定义提示信息
     *@param data 自定义提示数据
     *@return com.tontron.expo.common.dto.ApiResult<T>
     *
     *@author wangxulong duanxian0402@126.com
     *@version 20191130000
     *
     **/
    public static <T> ApiResult<T> success(String message, T data) {
        ApiResult<T> result = init();
        result.setCode(CommonApiStatusCode.SUCCESS.getCode());
        result.setMessage(message);
        result.setData(data);
        return result;
    }

    /*
     *默认失败返回

     *@return com.tontron.expo.common.dto.ApiResult<T>
     *
     *@author wangxulong duanxian0402@126.com
     *@version 20191130000
     *
     **/
    public static <T> ApiResult<T> error() {
        ApiResult<T> result = init();
        result.setCode(CommonApiStatusCode.ERROR.getCode());
        result.setMessage(CommonApiStatusCode.ERROR.getText());
        return result;
    }

    /*
     * 失败 返回自定义提示信息

     *@param message
     *@return com.tontron.expo.common.dto.ApiResult<T>
     *
     *@author wangxulong duanxian0402@126.com
     *@version 20191130000
     *
     **/
    public static <T> ApiResult<T> error(String message) {
        ApiResult<T> result = init();
        result.setCode(CommonApiStatusCode.ERROR.getCode());
        result.setMessage(message);
        return result;
    }

    /*
     * 自定义code\message\data 构造返回对象

     *@param code
     *@param message
     *@param data
     *@return com.tontron.expo.common.dto.ApiResult<T>
     *
     *@author wangxulong duanxian0402@126.com
     *@version 20191130000
     *
     **/
    public static <T> ApiResult<T> valueOf(int code, String message, T data) {
        ApiResult<T> result = init();
        result.setCode(code);
        result.setMessage(message);
        result.setData(data);
        return result;
    }

    /*
     * 自定义code和 message 返回对象

     *@param code
     *@param message
     *@return com.tontron.expo.common.dto.ApiResult<T>
     *
     *@author wangxulong duanxian0402@126.com
     *@version 20191130000
     *
     **/
    public static <T> ApiResult<T> valueOf(int code, String message) {
        ApiResult<T> result = init();
        result.setCode(code);
        result.setMessage(message);
        return result;
    }

}




