package universityproject.taskmanager.epic.controller;

import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import universityproject.taskmanager.epic.dto.CreateEpicRequest;
import universityproject.taskmanager.epic.dto.UpdateEpicRequest;
import universityproject.taskmanager.epic.model.Epic;
import universityproject.taskmanager.epic.service.EpicService;

@RestController
@RequestMapping("/api/v1/epics")
@RequiredArgsConstructor
public class EpicController {

    private final EpicService epicService;

    @PostMapping
    public ResponseEntity<Epic> createEpic(@Valid @RequestBody CreateEpicRequest request) {
        Epic epic = epicService.createEpic(request.title(), request.description(), request.projectId());
        return ResponseEntity.status(HttpStatus.CREATED).body(epic);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Epic> getEpicById(@PathVariable UUID id) {
        Epic epic = epicService.getEpicById(id);
        return ResponseEntity.ok(epic);
    }

    @GetMapping
    public ResponseEntity<List<Epic>> getAllEpics() {
        List<Epic> epics = epicService.getAllEpics();
        return ResponseEntity.ok(epics);
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<Epic>> getProjectEpics(@PathVariable UUID projectId) {
        List<Epic> epics = epicService.getProjectEpics(projectId);
        return ResponseEntity.ok(epics);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Epic> updateEpic(@PathVariable UUID id, @Valid @RequestBody UpdateEpicRequest request) {
        Epic epic = epicService.updateEpic(id, request.title(), request.description());
        return ResponseEntity.ok(epic);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEpic(@PathVariable UUID id) {
        epicService.deleteEpic(id);
        return ResponseEntity.noContent().build();
    }
}
