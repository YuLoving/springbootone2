package cn.nj.springbootone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.DateFormat;
import java.util.Date;

//@RestController
@Controller
public class helloController {


   /*
    问题： 这样写配置文件繁琐而且可能会造成类的臃肿，因为有许许多多的 @Value 注解。
   @Value("${name}")
    private String name;

    @Value("${age}")
    private  int age;*/

  /* @Autowired
   private StudentProperties studentProperties;

    @RequestMapping("/hello")
    public String hello(){
        return  this.studentProperties.getName()+ this.studentProperties.getAge();
    }*/
    @RequestMapping("/hello")
    public String hello(Model model){
        model.addAttribute("now", DateFormat.getTimeInstance().format(new Date()));
        return "hello";
    }



}
