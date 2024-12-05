package com.ennea.enneaservices.model.Json;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParserConfigJSON {

    private ParserConfigFileInfo fileInfo = new ParserConfigFileInfo();

    private HashMap<String, String> headerMap;

    public HashMap<String, List<String>> invertHeaderMap() {
        HashMap<String, List<String>> res = new HashMap<>();
        Map<String, String> headerMap = this.getHeaderMap();

        for(Map.Entry<String, String> entry : headerMap.entrySet()){
            String key = entry.getKey();
            String value = entry.getValue().toLowerCase();

            if(!res.containsKey(value)){
                res.put(value, new ArrayList<>());
            }
            res.get(value).add(key);
        }

        return res;
    }

}
