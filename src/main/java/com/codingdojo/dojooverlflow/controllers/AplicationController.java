package com.codingdojo.dojooverlflow.controllers;

import com.codingdojo.dojooverlflow.models.Answer;
import com.codingdojo.dojooverlflow.models.Question;
import com.codingdojo.dojooverlflow.models.QuestionTag;
import com.codingdojo.dojooverlflow.models.Tag;
import com.codingdojo.dojooverlflow.services.AnswerServices;
import com.codingdojo.dojooverlflow.services.QuestionServices;
import com.codingdojo.dojooverlflow.services.QuestionTagServices;
import com.codingdojo.dojooverlflow.services.TagServices;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class AplicationController {
    private final AnswerServices answerServices;
    private final QuestionServices questionServices;
    private final TagServices tagServices;
    private final QuestionTagServices questionTagServices;

    public AplicationController(AnswerServices answerServices, QuestionServices questionServices, TagServices tagServices, QuestionTagServices questionTagServices){
        this.answerServices =answerServices;
        this.questionServices = questionServices;
        this.tagServices = tagServices;
        this.questionTagServices = questionTagServices;
    }

    @GetMapping("/")
    public String renderIndex(Model viewModel){
        List<Question> listaQuestions = questionServices.allQuestions();
        viewModel.addAttribute("questions",listaQuestions);
        return "index";
    }

    //Nueva pregunta-----------------------------------------
    @GetMapping("/questions/new")
    public String renderNewQuestion(@ModelAttribute("questionTagObject") QuestionTag questionTagObject){
        return "newquestion";
    }

    @PostMapping("/questions/new")
    public String enviarFormNewQuestion(@Valid @ModelAttribute("questionTagObject") QuestionTag questionTagObject, BindingResult result){
        if(result.hasErrors()){
            System.out.println(result);
            return "newquestion";
        }else{
            Question questionDeForm = questionTagObject.getQuestion();
            Tag tagDeForm = questionTagObject.getTag();

            System.out.println(questionDeForm);
            System.out.println(tagDeForm);

            // Establecer la relación bidireccional TENGO DUDAS CON ESTO, NO ENTIENDO COMO RELACIONARLOS Y QUE SE GUARDEN.
           // questionDeForm.getTags().add(tagDeForm);
           // tagDeForm.getQuestions().add(questionDeForm);

            questionServices.crearQuestion(questionDeForm);
            tagServices.crearTag(tagDeForm);
            questionTagServices.guardadQuestionTag(questionTagObject);


            return "redirect:/";
        }
    }

   //Mostrar detalle de pregunta--------------------------------------------
    @GetMapping("/questions/{id}")
    public String renderDetalleQuestion(@PathVariable("id") Long id, Model viewModel, @ModelAttribute("answerForm") Answer answerForm){
        Question questionParaDetallar = questionServices.buscarQuestion(id);
        viewModel.addAttribute("questionDetallada", questionParaDetallar);
        List<Answer> listAnswers = answerServices.traerAnswersPorQuestion(id);

        viewModel.addAttribute("listaDeAnswers", listAnswers);

        return "questiondetail";
    }

    @PostMapping("/questions/{id}")
    public String postAnswer(@PathVariable Long id,@Valid @ModelAttribute("answerForm") Answer answerForm, BindingResult result){

        if(result.hasErrors()){
            return  "questiondetail";
        }else{
            Question questionDetalle = questionServices.buscarQuestion(id);

            Answer newAnswer = new Answer();//Hay que crear una nueva instancia porque se sobreescribia la misma answer
            newAnswer.setQuestion(questionDetalle);
            newAnswer.setAnswer(answerForm.getAnswer()); // Copiar el contenido del formulario
            answerServices.crearAnswer(newAnswer);

            return "redirect:/questions/"+id;
        }
    }

}
