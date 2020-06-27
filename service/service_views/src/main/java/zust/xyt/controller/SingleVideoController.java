package zust.xyt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author AndrewElvis
 * @date 2020-06-27-15:04
 * @description
 */
@Controller
public class SingleVideoController {
    @RequestMapping("/single-video")
    public String singleVideo(Model model) {

        model.addAttribute("url","https://outin-728f05bbae0011ea98ee00163e1c9256.oss-cn-shanghai.aliyuncs.com/sv/4a04c007-172f47af0e3/4a04c007-172f47af0e3.mp4?Expires=1593245207&OSSAccessKeyId=LTAIVVfYx6D0HeL2&Signature=dkCVcxxZlePc9LhtJC4kjee72tU%3D");
        return "single-video";
    }
}
