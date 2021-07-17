package top.b0x0.cloud.alibaba.provider.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.apache.dubbo.config.annotation.DubboService;
import org.apache.dubbo.rpc.RpcException;
import top.b0x0.cloud.alibaba.api.EchoService;
import top.b0x0.cloud.alibaba.common.exception.ExceptionUtil;

/**
 * @author ManJiis
 * @since 2021-07-16
 * @since 1.8
 */
//@DubboService(version = "${service.version}",validation = "CustomValidator")
@DubboService(version = "${service.version}")
public class EchoServiceImpl implements EchoService {

    @Override
    @SentinelResource(value = "sayHello",
            blockHandler = "exceptionHandler", blockHandlerClass = ExceptionUtil.class,
            fallback = "helloFallback", defaultFallback = "defaultFallback", exceptionsToIgnore = {})
//    @SentinelResource(value = "sayHello", blockHandler = "exceptionHandler", fallback = "helloFallback")
    // 原函数
    public String sayHello(String param) {
        if ("error".equals(param)) {
            throw new RpcException("error oops...");
        }
        return param;
    }

    // Fallback 函数，函数签名与原函数一致或加一个 Throwable 类型的参数.
    public String helloFallback(String s) {
        return String.format("EchoServiceImpl sayHello %s", s);
    }

    // Block 异常处理函数，参数最后多一个 BlockException，其余与原函数一致.
    public String exceptionHandler(String s, BlockException ex) {
        // Do some log here.
        ex.printStackTrace();
        return "Oops, error occurred at " + s;
    }

    public String helloFallback(String s, Throwable ex) {
        // Do some log here.
        ex.printStackTrace();
        return "Oops, error occurred at " + s;
    }

    public String defaultFallback() {
        System.out.println("Go to default fallback");
        return "default_fallback";
    }

}
