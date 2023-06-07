package uz.g22.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.g22.dto.BookRequestDto;
import uz.g22.entity.BookEntity;
import uz.g22.service.BookService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/book")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @PostMapping("/add")
    public ResponseEntity<BookEntity> add(@RequestBody BookRequestDto bookRequestDto){
        return ResponseEntity.ok(bookService.save(bookRequestDto));
    }
    @GetMapping("/get-all")
    public ResponseEntity<List<BookEntity>> getAll(
            @RequestParam int page,
            @RequestParam int size
    ){
        return ResponseEntity.status(200).body(bookService.getAll(page, size));
    }
    @GetMapping("/search")
    public ResponseEntity<List<BookEntity>> search(
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam String  name
    ){
        return ResponseEntity.status(200).body(bookService.search(page, size, name));
    }

    @PutMapping("/{bookId}/update")
    public ResponseEntity<BookEntity> update(
            @RequestBody BookRequestDto bookRequestDto,
            @PathVariable UUID bookId){
        return ResponseEntity.ok(bookService.update(bookRequestDto,bookId));
    }
    @DeleteMapping("/{bookId}/delete")
    public ResponseEntity deleteBook(@PathVariable UUID bookId) {
        bookService.delete(bookId);
        return ResponseEntity.status(204).build();
    }


}
