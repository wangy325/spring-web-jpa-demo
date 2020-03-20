package com.wangy.webmvc.controller;

import com.wangy.webmvc.data.SpittleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author wangy
 * @version 1.0
 * @date 2020/3/14 / 21:12
 */
@Controller
@RequestMapping("/spittles")
public class SpittleController {

    private static final String MAX_LONG_AS_STRING = Long.MAX_VALUE + "";
    private SpittleRepository spittleRepository;

    @Autowired
    public SpittleController(SpittleRepository spittleRepository) {
        this.spittleRepository = spittleRepository;
    }

    /**
     * 添加模型数据到view中，model可以使用键值为String类型的{@link java.util.Map}代替，效果是一样的。
     * 本例中model没有指定键值，会根据值的类型推断，因为值是List&lt;Spittle&gt;,所以键值会推断为spittleList，
     * 键值的生成机制见{@link org.springframework.core.Conventions#getVariableName}
     *
     * @param model
     * @return 视图名
     * @see Model
     * @see org.springframework.web.servlet.ModelAndView
     * @see ModelMap
     */
    @RequestMapping(method = RequestMethod.GET)
    public String spittles(Model model) {
        model.addAttribute(spittleRepository.getSpittles(1L, 20));
        return "spittles";
    }

    /**
     * 查询参数 @RequestParam
     *
     * @param model
     * @param maxId
     * @param count
     * @return
     */
    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public String getPageSpittles(Model model,
                                  @RequestParam(value = "maxId", defaultValue = MAX_LONG_AS_STRING) long maxId,
                                  @RequestParam(value = "count", defaultValue = "20") int count) {
        model.addAttribute(spittleRepository.getSpittles(maxId, count));
        return "spittles";
    }

    /**
     * 路径参数 使用占位符{}和@PathVariable，需要注意的是，@PathVariable所指定的参数名若和占位符里不一致，需要指明:<br>
     *     {@code @PathVariable(value="spittleId" long id)}
     *
     * @param spittleId
     * @param model
     * @return
     */
    @RequestMapping(value = "/{spittleId}", method = RequestMethod.GET)
    public String getOneSpittle(@PathVariable long spittleId, Model model) {
        model.addAttribute(spittleRepository.findOne(spittleId));
        return "spittle";
    }
}
