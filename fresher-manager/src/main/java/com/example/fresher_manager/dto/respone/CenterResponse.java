package com.example.fresher_manager.dto.respone;

import com.example.fresher_manager.entity.Center;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CenterResponse {
    private Long id;
    private String name;
    public static CenterResponse fromEntity(Center center) {
        CenterResponse response = new CenterResponse();
        response.setId(center.getId());
        response.setName(center.getName());
        return response;
    }
}
