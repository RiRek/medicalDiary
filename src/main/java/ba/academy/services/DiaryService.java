package ba.academy.services;

import ba.academy.dto.Diary;

import java.util.List;

public interface DiaryService {

    List<Diary>getDiary();
    Diary getDiaryById(Long id);
    boolean deleteDiary(Long id);
    Diary updateDiary(Long id,Diary diaryInput);
    Diary createDiary(Diary input);
}
