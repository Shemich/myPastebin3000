package ru.shemich.mypastebin3000;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.shemich.mypastebin3000.api.response.MyPastebin3000Response;
import ru.shemich.mypastebin3000.exception.ModelNotFoundException;
import ru.shemich.mypastebin3000.model.Paste;
import ru.shemich.mypastebin3000.repository.MyPastebin3000Repository;
import ru.shemich.mypastebin3000.service.MyPastebin3000Service;
import ru.shemich.mypastebin3000.service.impl.MyPastebin3000ServiceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
public class MyPastebin3000ServiceTest {
    @Autowired
    private MyPastebin3000Service pastebin3000Service;

    @MockBean
    private MyPastebin3000Repository pastebin3000Repository;
    @Test
    public void notExistHash(){
        when(pastebin3000Repository.getByHash(anyString())).thenThrow(ModelNotFoundException.class);
        assertThrows(ModelNotFoundException.class, () -> pastebin3000Service.getByHash("dl,awkdmaowdm"));
    }

    @Test
    public void getExistHash(){
        Paste paste = new Paste();
        paste.setHash("1");
        paste.setData("11");
        paste.setPublic(true);

        when(pastebin3000Repository.getByHash("1")).thenReturn(paste);

        MyPastebin3000Response expected = new MyPastebin3000Response("11", true);
        MyPastebin3000Response actual = pastebin3000Service.getByHash("1");

        assertEquals(expected, actual);

    }
}
