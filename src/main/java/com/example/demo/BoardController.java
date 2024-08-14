package com.example.demo.controller;

import com.example.demo.entity.Board;
import com.example.demo.service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/boards")
public class BoardController {
    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping
    public String listBoards(Model model) {
        model.addAttribute("boards", boardService.findAll());
        return "boards/list";
    }

    @GetMapping("/{id}")
    public String viewBoard(@PathVariable Long id, Model model) {
        Board board = boardService.findById(id);
        model.addAttribute("board", board);
        return "boards/view";
    }

    @PostMapping
    public String createBoard(Board board) {
        boardService.save(board);
        return "redirect:/boards";
    }
}
