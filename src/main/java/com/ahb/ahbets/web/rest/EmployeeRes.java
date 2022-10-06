package com.ahb.ahbets.web.rest;

import com.ahb.ahbets.domain.*;
import com.ahb.ahbets.repository.*;
import com.ahb.ahbets.service.EmployeService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
@Transactional
public class EmployeeRes {
    private static final String ENTITY_NAME = "Employee";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;
    int available;
    private final RequestRepository reqRepo;
    private final HospitalRepository hospRepo;
    private final DepartmentRepository depRepo;

    private final ResultRepo resultRep;
    private final ChoiceRepo choiceRepo;
    private final EmployeeRepo emprepo;
    private final EmployeService empServ;

    public EmployeeRes(EmployeeRepo emprepo,EmployeService empServ,
                       RequestRepository reqRepo,ChoiceRepo choiceRepo,
                       ResultRepo resultRep,DepartmentRepository depRepo
    ,HospitalRepository hospRepo) {
        this.emprepo = emprepo;
        this.choiceRepo = choiceRepo;
        this.reqRepo = reqRepo;
        this.empServ = empServ;
        this.resultRep=resultRep;
        this.depRepo=depRepo;
        this.hospRepo=hospRepo;
    }

    @PostMapping("/addemp")
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee emp) throws URISyntaxException {
//        emp.setFile(emp.getFileContentType().getBytes());
        Employee reslt = empServ.save(emp);
        return ResponseEntity
            .created(new URI("/api/requests/" + reslt.getUserName()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, reslt.getUserName().toString()))
            .body(reslt);
    }


    @GetMapping("/getAllData")
    public List<ReqHosp> getDbdata(){
        List<ReqHosp> reqHopspList = new ArrayList<>();
        List<String> userName = new ArrayList<>();
        for(ReqHosp req:reqRepo.getJoinData()){
            if(!userName.contains(req.getUserName())){
               reqHopspList.add(req) ;
                userName.add(req.getUserName());
            }
        }
        return reqHopspList;
    }


public List<Disp> trial0(int available,long code,String hospName ,String profession){
    List<CookObject> cooked = cook();
    List<Filtered> filList=new ArrayList<>();
    List<Disp> newFilList=new ArrayList<>();
    List<Disp> dispList=new ArrayList<>();


    for(int l=0;l<cooked.size();l++){
        if(cooked.get(l).getCode()==code && cooked.get(l).getProfession().equalsIgnoreCase(profession)){
            filList = reqRepo.getFiltered(code);
            for(int j=0;j<filList.size();j++){
                dispList.add(new Disp(filList.get(j).getUserName(),filList.get(j).getTotal(), hospName));
            }
            if(filList.size()<=available){
                newFilList.addAll(dispList);
            }else{
               for(int k=0;k<available;k++){
                   newFilList.add(dispList.get(k));
               }

            }
          break;
        }
    }

return newFilList;
}
   @GetMapping("/getSelectedEmp")
public List<Disp> trial2(){
       List<CookObject> cooked = cook();
        List<Disp> finalFiltered = new ArrayList<Disp>();
    List<Hospital> hospList = getHospital();
    List<Department>depList = getDepartment();
    for(int i=0;i<hospList.size();i++){
        for(int l= 0;l<depList.size();l++){
            if(hospList.get(i).getCode().equals(depList.get(l).getHcode())){
                System.out.println("++++++++++++++++++++++++++++ entered");
                finalFiltered.addAll(trial0(depList.get(l).getAvailable(),depList.get(l).getHcode(),hospList.get(i).getHospitalName(),depList.get(l).getDepName()));
            }

        }
    }
   return finalFiltered;
}
List<Hospital> getHospital(){
        return hospRepo.findAll();
}
List<Department>getDepartment(){
        return depRepo.findAll();
}

public List<CookObject> cook(){
        List<CookObject> cookedList = new ArrayList<>();
        List<Employee> employes = getEmployes();
        List<Choice> choice = getAllChoice();
        List<Result> result = getRes();
        for(int i=0;i< choice.size();i++){
           for(int l =0;l<result.size();l++){
              if(choice.get(i).getEmpChoic().equalsIgnoreCase(result.get(l).getUserName())){
                 CookObject object = new CookObject(choice.get(i).getEmpChoic(),choice.get(i).getHospitalName(),result.get(l).getTotalV(),choice.get(i).getHos(),choice.get(i).getProfession());
                 cookedList.add(object);
            }
        }
    }
//   System.out.println("++++++++++++++++++++"+cookedList.get(0).toString());
    return cookedList;
}

    public List<empSelected> getSelectedEmp(){
        return reqRepo.getEmpSelected();
}

public List<Choice> getAllChoice(){
        return choiceRepo.findAll();
}

public List<Result> getRes(){
      return  resultRep.findAll();
}

public List<Employee> getEmployes(){
        return emprepo.findAll();
}


}
