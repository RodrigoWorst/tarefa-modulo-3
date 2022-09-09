package com.devsuperior.bds04.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.devsuperior.bds04.dto.CityDTO;
import com.devsuperior.bds04.resources.exceptions.FieldMessage;

public class CityInsertValidator implements ConstraintValidator<CityInsertValid, CityDTO> {
	
	@Autowired
	private HttpServletRequest request;
	
	@Override
	public void initialize(CityInsertValid ann) {
	}

	@Override
	public boolean isValid(CityDTO dto, ConstraintValidatorContext context) {
		
		List<FieldMessage> list = new ArrayList<>();
		
		if(dto.getName().isBlank()) {
			list.add(new FieldMessage("name", "Campo requerido"));
		}
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}