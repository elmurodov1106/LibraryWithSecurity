package uz.g22.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import uz.g22.dto.BookRequestDto;
import uz.g22.entity.BookEntity;
import uz.g22.exception.DataNotFoundException;
import uz.g22.repositoty.BookRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;

    public BookEntity save(BookRequestDto bookDto){
        BookEntity map = modelMapper.map(bookDto, BookEntity.class);
       return bookRepository.save(map);
    }

    public List<BookEntity> getAll(int page,int size){
        Pageable pageable  =  PageRequest.of(page,size);
        return bookRepository.findAll(pageable).getContent();
    }

    public List<BookEntity> search(int page,int size,String name){
        Sort sort =Sort.by(Sort.Direction.ASC,"name");
       Pageable pageable =  PageRequest.of(page,size,sort);
       return bookRepository.searchBookEntitiesByNameContainingIgnoreCase(name,pageable);
    }
    public void delete(UUID id){
        bookRepository.deleteById(id);
    }
    public BookEntity update(BookRequestDto update,UUID id){
//        BookEntity bookEntity = modelMapper.map(update, BookEntity.class);
        BookEntity bookEntity = bookRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("user not found"));
        modelMapper.map(update, BookEntity.class);
        return bookRepository.save(bookEntity);
    }
}
