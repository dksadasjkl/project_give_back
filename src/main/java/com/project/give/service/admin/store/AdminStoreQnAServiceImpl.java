package com.project.give.service.admin.store;

import com.project.give.entity.StoreProductQna;
import com.project.give.repository.StoreProductQnaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AdminStoreQnAServiceImpl implements AdminStoreQnAService {

    @Autowired
    private StoreProductQnaMapper storeProductQnaMapper;

    @Override
    public Map<String, Object> getQnAList(int page, int size, Integer productId) {
        int offset = (page - 1) * size;

        List<StoreProductQna> list =
                storeProductQnaMapper.selectQnAListAdmin(offset, size, productId);

        int total = storeProductQnaMapper.countQnAAdmin(productId);

        Map<String, Object> map = new HashMap<>();
        map.put("items", list);
        map.put("total", total);

        return map;
    }

    @Override
    public StoreProductQna getQnADetail(int qnaId) {
        return storeProductQnaMapper.selectQnADetailAdmin(qnaId);
    }

    @Override
    public void answerQnA(int qnaId, String answer) {
        storeProductQnaMapper.insertAnswerAdmin(qnaId, answer);
    }

    @Override
    public void updateAnswer(int qnaId, String answer) {
        storeProductQnaMapper.updateAnswerAdmin(qnaId, answer);
    }

    @Override
    public void deleteQnA(int qnaId) {
        storeProductQnaMapper.deleteQnAAdmin(qnaId);
    }
}
