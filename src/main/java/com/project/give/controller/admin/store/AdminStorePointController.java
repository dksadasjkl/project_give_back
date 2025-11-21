package com.project.give.controller.admin.store;

import com.project.give.entity.StorePoint;
import com.project.give.repository.StorePointMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/store/points")
public class AdminStorePointController {

    @Autowired
    private StorePointMapper storePointMapper;

    @GetMapping
    public ResponseEntity<?> getPointList(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "20") int size
    ) {
        int offset = (page - 1) * size;

        List<StorePoint> list = storePointMapper.selectAllPointsPaged(offset, size);
        int total = storePointMapper.countAllPoints();

        Map<String, Object> map = new HashMap<>();
        map.put("items", list);
        map.put("total", total);

        return ResponseEntity.ok(map);
    }
}
