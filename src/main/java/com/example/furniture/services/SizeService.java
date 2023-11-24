package com.example.furniture.services;

import com.example.furniture.models.Size;
import com.example.furniture.repositories.SizeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class SizeService {
    private final SizeRepository sizeRepository;

    public List<Size> listSize(Long id_size){
        return sizeRepository.findAll();
    }

    public void saveSize(Size size){
        sizeRepository.save(size);
    }

    public void deleteSize(Long id_size){
        sizeRepository.deleteById(id_size);
    }

    public void editSize(Long id_size, Size size){
        Size newSize = getSizeById(id_size);
        newSize.setLength(size.getLength());
        newSize.setWidth(size.getWidth());
        newSize.setHeight(size.getHeight());
        sizeRepository.save(size);
    }

    public Size getSizeById(Long id){
        return sizeRepository.findById(id).orElse(null);
    }
}
