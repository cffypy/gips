package top.geomatics.ips.server;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.geomatics.ips.server.util.CSVToJSon;

/**
 * @author chenfa
 * 数据转换服务
 */
@Api(value = "/transform", tags = "格式转换服务")
@RestController
@RequestMapping("/transform")
public class TransformController {

    @ApiOperation(value = "csv转json", notes = "csv转json")
    @PostMapping("/csv2json")
    public void csv2json(@RequestParam String filePath,@RequestParam String outPutPath) throws Exception {
        CSVToJSon csvToJSon = new CSVToJSon();
        csvToJSon.ConvertToJson(filePath, outPutPath);
    }




}
