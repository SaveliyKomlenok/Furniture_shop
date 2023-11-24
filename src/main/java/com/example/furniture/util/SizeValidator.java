package com.example.furniture.util;

import com.example.furniture.models.Size;
import com.example.furniture.repositories.SizeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class SizeValidator implements Validator {
    private final SizeRepository sizeRepository;
    @Override
    public boolean supports(Class<?> clazz) {
        return Size.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Size size = (Size) target;

        Size duplicateSize = sizeRepository.findSizeByLengthAndWidthAndHeight(size.getLength(), size.getWidth(), size.getHeight());

        if(duplicateSize != null && !Objects.equals(duplicateSize.getId_size(), size.getId_size())){
            errors.rejectValue("length", "", "Данные размеры уже существует");
        }

        if(size.getId_size() != null){
            if (!sizeRepository.existsById(size.getId_size())) {
                errors.rejectValue("id_size", "", "Введите существующий номер размеров");
            }
        }
    }
}
