package finalkeyword;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FinalKeywordTest {

    @Test
    public void 메서드_내_final_변수_재할당_불가() {
        final Message message = new Message("hello");
        // message = new Message(); // error: cannot assign a value to final variable message
    }

    @Test
    public void 메서드_내_final_변수의_속성_변경_가능() {
        final Message message = new Message("hello");
        String expectedContents = "bye";
        message.changeContents(expectedContents);
        assertThat(message.getContents()).isEqualTo(expectedContents);
    }

    @Test
    public void final_객체를_전달하고_전달받은쪽에서_참조를_변경해도_호출하는_쪽은_영향_받지_않는다() {
        final Message message = new Message("hello");
        String originContents = message.getContents();
        noFinalKeywordOnParameterAndChangeReference(message);

        assertThat(message.getContents()).isEqualTo(originContents);
    }

    @Test
    public void final_객체를_전달하고_전달받은쪽에서_속성을_변경하면_호출하는_쪽_객체의_속성도_변한다() {
        final Message message = new Message("hello");
        String originContents = message.getContents();

        noFinalKeywordOnParameterAndChangeProperty(message);
        assertThat(message.getContents()).isNotEqualTo(originContents);

        finalKeywordOnParameterAndChangeProperty(message);
        assertThat(message.getContents()).isNotEqualTo(originContents);
    }

    private void finalKeywordOnParameter(final Message message) {
        // message = new Message(); // error: cannot assign a value to final variable message
    }

    private void finalKeywordOnParameterAndChangeProperty(final Message message) {
        message.changeContents("barfoo");
    }

    private void noFinalKeywordOnParameterAndChangeReference(Message message) {
        message = new Message("new contents");
    }

    private void noFinalKeywordOnParameterAndChangeProperty(Message message) {
        message.changeContents("barfoo");
    }


    public class Message {
        private String contents;

        public Message(String contents) {
            this.contents = contents;
        }

        public void changeContents(String contents) {
            this.contents = contents;
        }

        public String getContents() {
            return contents;
        }
    }
}

