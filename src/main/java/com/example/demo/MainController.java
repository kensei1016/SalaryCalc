package com.example.demo;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller // This means that this class is a Controller
//@RequestMapping(path = "/demo") // This means URL's start with /demo (after Application path)
public class MainController {

  @Autowired // This means to get the bean called userRepository
  MstEmployeeRepository employeeRepository;

  private JdbcTemplate jdbcTemplate;

  /* GET */
  @RequestMapping(value = "/", method = RequestMethod.GET)
  public ModelAndView index(@ModelAttribute("formModel") MstEmployee employee, ModelAndView mav) {
    mav.setViewName("index");
    mav.addObject("msg", "this is sample content.");
    Iterable<MstEmployee> list = employeeRepository.findAll();
    mav.addObject("emplist", list);

    return mav;
  }

  /* POST */
  @RequestMapping(value = "/", method = RequestMethod.POST)
  @Transactional(readOnly = false)
  public ModelAndView form(@ModelAttribute("formModel") MstEmployee employee, ModelAndView mav) {
    employeeRepository.saveAndFlush(employee);

    return new ModelAndView("redirect:/");
  }

  /* SHOW */
  @RequestMapping(value = "/employee/{id}", method = RequestMethod.GET)
  public ModelAndView show(@ModelAttribute MstEmployee employee, @PathVariable int id, ModelAndView mav) {
    mav.setViewName("show");
    mav.addObject("title", "show mydata");
    Optional<MstEmployee> data = employeeRepository.findById(id);
    mav.addObject("formModel", data.get());

    return mav;
  }

  /* EDIT */
  @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
  public ModelAndView edit(@ModelAttribute MstEmployee employee, @PathVariable int id, ModelAndView mav) {
    mav.setViewName("edit");
    mav.addObject("title", "edit mydata");
    Optional<MstEmployee> data = employeeRepository.findById(id);
    mav.addObject("formModel", data.get());

    return mav;
  }

  /* UPDATE */
  @RequestMapping(value = "/edit", method = RequestMethod.POST)
  @Transactional(readOnly=false)
  public ModelAndView update(@ModelAttribute MstEmployee employee, ModelAndView mav) {
    employeeRepository.saveAndFlush(employee);

    return new ModelAndView("redirect:/");
  }

  /* DELETE */
  @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
  @Transactional(readOnly=false)
  public ModelAndView remove(@PathVariable int id, ModelAndView mav) {
    employeeRepository.deleteById(id);

    return new ModelAndView("redirect:/");
  }

  //  @GetMapping("/")
  //  public String index(Model model, @ModelAttribute("formModel") Employee mydata) {
  //
  //    //		List<Employee> list = jdbcTemplate.queryForObject("select * from employee", requiredType)
  //    List<Employee> emplist = (List<Employee>) employeeRepository.findAll();
  //    model.addAttribute("emplist", emplist);
  //
  //    return "index";
  //  }
  //
  //  @PostMapping(path = "/add") // Map ONLY POST Requests
  //  public @ResponseBody String addNewEmployee(@RequestParam String name, @RequestParam String email) {
  //    // @ResponseBody means the returned String is the response, not a view name
  //    // @RequestParam means it is a parameter from the GET or POST request
  //
  //    Employee n = new Employee();
  //    n.setName(name);
  //    employeeRepository.save(n);
  //    return "Saved";
  //  }
  //
  //  @GetMapping(path = "/all")
  //  public @ResponseBody Iterable<Employee> getAllEmployees() {
  //    // This returns a JSON or XML with the users
  //    return employeeRepository.findAll();
  //  }
}