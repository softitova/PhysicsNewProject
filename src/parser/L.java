package parser;
import model.*;
/**
 * Created by vadim on 26.11.16.
 */
public enum L {
    val, var, plus('+'), power('^'), minus('-'), mult('*'), div('/'), ln, open('('), close(')'), end;

    final char value;
    L() {
        value = 0;
    }
    L(char value) {
        this.value = value;
    }
}
