package net;

import static org.assertj.core.api.Assertions.assertThat;

import org.apache.tika.Tika;
import org.junit.Before;
import org.junit.Test;

import javax.activation.MimetypesFileTypeMap;
import java.net.URLConnection;

public class ContentTypeTest {

    private Tika tika;

    @Before
    public void setUp() {
        tika = new Tika();
    }

    @Test
    public void 이미지파일_확장자로_컨텐츠_타입을_얻는다() {
        assertThat(guessContentType(".jpg")).isEqualTo("image/jpeg");
        assertThat(byMimetypesFileTypeMap(".jpg")).isEqualTo("image/jpeg");
        assertThat(tika.detect(".jpg")).isEqualTo("image/jpeg");


        assertThat(guessContentType(".jpeg")).isEqualTo("image/jpeg");
        assertThat(byMimetypesFileTypeMap(".jpeg")).isEqualTo("image/jpeg");
        assertThat(tika.detect(".jpg")).isEqualTo("image/jpeg");


        assertThat(guessContentType(".gif")).isEqualTo("image/gif");
        assertThat(byMimetypesFileTypeMap(".gif")).isEqualTo("image/gif");
        assertThat(tika.detect(".gif")).isEqualTo("image/gif");


        assertThat(guessContentType(".png")).isEqualTo("image/png");
        assertThat(byMimetypesFileTypeMap(".png")).isEqualTo("application/octet-stream");
        assertThat(tika.detect(".png")).isEqualTo("image/png");


        assertThat(guessContentType(".exif")).isNull();
        assertThat(byMimetypesFileTypeMap(".exif")).isEqualTo("application/octet-stream");
        assertThat(tika.detect(".exif")).isEqualTo("application/octet-stream");


        assertThat(guessContentType(".bmp")).isEqualTo("image/bmp");
        assertThat(byMimetypesFileTypeMap(".bmp")).isEqualTo("application/octet-stream");
        assertThat(tika.detect(".bmp")).isEqualTo("image/bmp");
    }

    @Test
    public void 파일_확장자로_컨텐츠_타입을_얻는다() {
        assertThat(guessContentType(".xls")).isNull();
        assertThat(byMimetypesFileTypeMap(".xls")).isEqualTo("application/octet-stream");
        assertThat(tika.detect(".xls")).isEqualTo("application/vnd.ms-excel");
    }

    private String guessContentType(String fileName) {
        return URLConnection.guessContentTypeFromName(fileName);
    }

    private String byMimetypesFileTypeMap(String fileName) {
        return MimetypesFileTypeMap.getDefaultFileTypeMap().getContentType(fileName);
    }
}
