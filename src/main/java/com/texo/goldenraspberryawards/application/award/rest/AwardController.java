package com.texo.goldenraspberryawards.application.award.rest;

import com.texo.goldenraspberryawards.application.award.openapi.AwardControllerOpenApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(path = AwardController.PATH)
public class AwardController implements AwardControllerOpenApi {

	public static final String PATH = "awards";

}
