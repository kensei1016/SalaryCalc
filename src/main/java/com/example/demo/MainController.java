package com.example.demo;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller // This means that this class is a Controller
//@RequestMapping(path = "/demo") // This means URL's start with /demo (after Application path)
public class MainController {

  @Autowired // This means to get the bean called userRepository
  MstEmployeeRepository employeeRepository;

  @Autowired
  TrnPayslipRepository trnPayslipRepository;

  @Autowired
  private MstEmployeeService service;

  /*
   * GET(/)
   * メイン(支給日一覧)画面
   */
  @RequestMapping(value = "/", method = RequestMethod.GET)
  public ModelAndView index(@ModelAttribute("formModel") MstEmployee employee, ModelAndView mav) {
    mav.setViewName("index");
    mav.addObject("msg", "this is sample content.");
    List<MstEmployee> list = service.getAll();
    mav.addObject("emplist", list);

    return mav;
  }


  /*
   * GET(/emplist?date=202001)
   * 従業員一覧画面
   */
  @RequestMapping(value = "/emplist", params = "date", method = RequestMethod.GET)
  public ModelAndView emplist(@ModelAttribute("formModel") @RequestParam Integer date, ModelAndView mav) {
    mav.setViewName("emplist");
    mav.addObject("msg", "this is sample content.");
    mav.addObject("date", date);

//    List<TrnPayslip> list = trnPayslipRepository.findByYearMonth(date);
    List<MstEmployee> list = service.getAll();
    mav.addObject("trnPayslipList", list);

    return mav;
  }

  /*
   * GET(/emp_salary?date=202001&emp_id=1)
   * 従業員給与画面
   */
  @RequestMapping(value = "/emp_salary", params = {"date", "emp_id"}, method = RequestMethod.GET)
  public ModelAndView emp_salary(@RequestParam Integer date,
                                  @RequestParam Integer emp_id,
                                  ModelAndView mav) {
    mav.setViewName("salary_show");
    mav.addObject("msg", "this is sample content.");
    Optional<TrnPayslip> data = trnPayslipRepository.findByYearMonth(202001, 2);
    mav.addObject("trnPayslip", data.get());

    return mav;
  }

  /* POST */
  @RequestMapping(value = "/emp_salary", method = RequestMethod.POST)
  @Transactional(readOnly = false)
  public ModelAndView form(@ModelAttribute("trnPayslip") TrnPayslip trnPayslip,
                            ModelAndView mav) {
    trnPayslipRepository.saveAndFlush(trnPayslip);

    return new ModelAndView("redirect:/");
  }

  /* GET(FIND) */
  @RequestMapping(value = "/find", method = RequestMethod.GET)
  public ModelAndView find(ModelAndView mav) {
	  mav.setViewName("find");
	  mav.addObject("title", "Find Page");
	  mav.addObject("msg", "MyDataのサンプルです。");
	  mav.addObject("value", "");
	  List<MstEmployee> list = service.getAll();
	  mav.addObject("datalist", list);

	  return mav;
  }

  /* POST(FIND) */
  @RequestMapping(value = "/find", method = RequestMethod.POST)
  public ModelAndView search(HttpServletRequest request, ModelAndView mav) {
	  mav.setViewName("find");
	  String param = request.getParameter("fstr");
	  if(param == "") {
		  mav = new ModelAndView("redirect:/find");
	  } else {
		  mav.addObject("title", "Find result");
		  mav.addObject("msg", "「" + param + "「の検索結果");
		  mav.addObject("value", param);
		  List<MstEmployee> list = service.find(param);
		  mav.addObject("datalist", list);
	  }

	  return mav;
  }

//  /* POST */
//  @RequestMapping(value = "/", method = RequestMethod.POST)
//  @Transactional(readOnly = false)
//  public ModelAndView form(@ModelAttribute("formModel") MstEmployee employee, ModelAndView mav) {
//    employeeRepository.saveAndFlush(employee);
//
//    return new ModelAndView("redirect:/");
//  }
//
//  /* SHOW */
//  @RequestMapping(value = "/employee/{id}", method = RequestMethod.GET)
//  public ModelAndView show(@ModelAttribute MstEmployee employee, @PathVariable int id, ModelAndView mav) {
//    mav.setViewName("show");
//    mav.addObject("title", "show mydata");
//    Optional<MstEmployee> data = employeeRepository.findById(id);
//    mav.addObject("formModel", data.get());
//
//    return mav;
//  }
//
//  /* EDIT */
//  @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
//  public ModelAndView edit(@ModelAttribute MstEmployee employee, @PathVariable int id, ModelAndView mav) {
//    mav.setViewName("edit");
//    mav.addObject("title", "edit mydata");
//    Optional<MstEmployee> data = employeeRepository.findById(id);
//    mav.addObject("formModel", data.get());
//
//    return mav;
//  }
//
//  /* UPDATE */
//  @RequestMapping(value = "/edit", method = RequestMethod.POST)
//  @Transactional(readOnly=false)
//  public ModelAndView update(@ModelAttribute MstEmployee employee, ModelAndView mav) {
//    employeeRepository.saveAndFlush(employee);
//
//    return new ModelAndView("redirect:/");
//  }
//
//  /* DELETE */
//  @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
//  @Transactional(readOnly=false)
//  public ModelAndView remove(@PathVariable int id, ModelAndView mav) {
//    employeeRepository.deleteById(id);
//
//    return new ModelAndView("redirect:/");
//  }

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