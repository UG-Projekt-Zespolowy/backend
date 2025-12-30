package universityproject.taskmanager.epic.controller;

import jakarta.validation.Valid;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import universityproject.taskmanager.epic.dto.CreateEpicRequest;
import universityproject.taskmanager.epic.dto.EpicResponse;
import universityproject.taskmanager.epic.dto.UpdateEpicRequest;
import universityproject.taskmanager.epic.mapper.EpicMapper;
import universityproject.taskmanager.epic.model.Epic;
import universityproject.taskmanager.epic.service.EpicService;

@RestController
@RequestMapping("/api/v1/epics")
@RequiredArgsConstructor
public class EpicController {

    private final EpicService epicService;

    @PostMapping
    public ResponseEntity<EpicResponse> createEpic(@Valid @RequestBody CreateEpicRequest request) {
        Epic epic = epicService.createEpic(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(EpicMapper.toResponse(epic));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EpicResponse> getEpicById(@PathVariable UUID id) {
        return ResponseEntity.ok(EpicMapper.toResponse(epicService.getEpicById(id)));
    }

    @GetMapping
    public ResponseEntity<Page<EpicResponse>> getAllEpics(Pageable pageable) {
        Page<EpicResponse> responses = epicService.getAllEpics(pageable).map(EpicMapper::toResponse);
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<Page<EpicResponse>> getProjectEpics(@PathVariable UUID projectId, Pageable pageable) {
        Page<EpicResponse> responses =
                epicService.getProjectEpics(projectId, pageable).map(EpicMapper::toResponse);
        return ResponseEntity.ok(responses);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EpicResponse> updateEpic(
            @PathVariable UUID id, @Valid @RequestBody UpdateEpicRequest request) {
        Epic epic = epicService.updateEpic(id, request);
        return ResponseEntity.ok(EpicMapper.toResponse(epic));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEpic(@PathVariable UUID id) {
        epicService.deleteEpic(id);
        return ResponseEntity.noContent().build();
    }
}
