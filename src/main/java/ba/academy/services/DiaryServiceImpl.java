package ba.academy.services;

import ba.academy.dto.Diary;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class DiaryServiceImpl implements DiaryService {
    public static List<Diary> diaries=new ArrayList<>();

    @Override
    public List<Diary> getDiary() {
        return diaries;
    }

    @Override
    public Diary getDiaryById(Long id) {
        return diaries.stream().filter(object ->id.equals(object.getId().toString())).findFirst().get();
    }

    @Override
    public boolean deleteDiary(Long id) {
        Optional<Diary> diaryToDelete=diaries.stream().filter(diary ->diary.getId().equals(id)).findFirst();
        boolean removed=false;
        if(diaryToDelete.isPresent()){
            diaries.remove(diaryToDelete.get());
            removed=true;
        }
        if(removed){
            return true;
        }
        return false;


    }

    @Override
    public Diary updateDiary(Long id, Diary diaryInput) {
        diaries=diaries.stream().map(diary -> {
            if(diary.getId().toString().equals(id)){
                diary.setTitle(diaryInput.getTitle());
                diary.setCount(diaryInput.getCount());
                diary.setMedicines(diaryInput.getMedicines());
                diary.setMedicineType(diaryInput.getMedicineType());

            }

            return diary;
        }).collect(Collectors.toList());

        return null;
    }

    @Override
    public Diary createDiary(Diary input) {
        diaries.add(input);
        return input;

    }
}
