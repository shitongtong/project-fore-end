package cn.stt.mail.util;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @Author shitongtong
 * <p>
 * Created by shitongtong on 2017/8/11.
 */
public class ImageToBase64 {

    public static void main(String[] args) {
        File imageFile = new File("D:\\图片\\小幺鸡3.png");
        String base64 = imageToBase64(imageFile);
        System.out.println(base64);

        String imageBase64 = "iVBORw0KGgoAAAANSUhEUgAAATAAAAEKCAYAAACVGgk4AAAgAElEQVR4nOydd5hcZdm479Omt53t\n" +
                "fbPZTTnpHdITShI6CCooWBBUiuVnoSkaFRAFP/kQPhVEReyI9F5DiyGEQMKQnmyym+29TTvn/P6Y\n" +
                "JCZkk8yWKbt77uvKlZ2Zc973OTNnnnne532KgIlJilEhH1jltkqnArMimpEfjOouQBAAWRKCiih0\n" +
                "WiSxVjOMjZ0h7V1gHfBOALRUym6SWoRUC2AyOlFj995Zbqv0/d6IPlPNsYfnFLrs5X4bBW4LGXYZ\n" +
                "UQDdgO6wRkdIo7YzTFVbiF2tofDm+h6tJ6xrosjveyP6HQHYm+prMkk+pgIzSToqzHIo4h/tijj2\n" +
                "M1OzbadWeHFZpH6Ps705yMObmyOv7G43rLJ4d3dYuykAoQSIbJKmmArMJGmoIFgk4XoDVl8+M0e5\n" +
                "YFImsjj4W7C2M8zP39gf2t4crO8Oa2cFYNMQiGsyDDAVmElSUEFyKOIfXBbpop+eXmoty7AO6fgG\n" +
                "8Fighd+8U98b0Y1PbDaMZ4Z0ApO0pP92u4nJAChVxD/4HfIn7zm73Jrntgz5+AIwIdvOhGy7smZP\n" +
                "x0XZUFVv8MGQT2SSVpgW2ChHBRuw3CaLpyuSMFEUsGg6LV1h7Q3g6QBsHewc0yThWx6rdMt95421\n" +
                "+mzy4IU+AR819vLtZ/eEQ1Hjk5sN47GET2iSMkwFNkpRofjADuBlhR6LNr/E7SjwWJAEgc6Qxqb6\n" +
                "7tB/qrtEqyR+0BXWvhGANwY4z2RFEjbcc3a5UuG3DfVlHJP3aru54fmqcFgzFgfgP0mb2CSpmAps\n" +
                "lKGCbJXFG3TD+N4p5V7xkmnZcpGn7yVdT0TnyS0t/P69xrAk8FBPRL86AMF+zCW6LNKmT0zyq5+b\n" +
                "kTNk1xAvz2xr5e61dW3BqD4xAHVJF8Ak4ZgKbBShQpFDEZ/Nc1kqrl9SaI3XImrqifDDl/cF97SG\n" +
                "dvZE9GUBaIznvMmCcHGWU/79QxdWWodit3Eg/OLN/doruzs2dIe1kwKgp0QIk4RhOvFHCSrMs0jC\n" +
                "2tMqMgp/fGqxkuVQ4j7XoUicXuGTd7eGMmq7Ihf5deOvjdBzgvkkh0V66pp5ef6xSVw6fpxZhS7x\n" +
                "+R1tWeGoEawzjLdSJohJQhBTLYBJ4lFhuSwKr315Tp7nm/PzxYFYQ7Io8L1lRcrCUneJQxFfVsF+\n" +
                "glPOcChi1rIx3oEJPURYJIHvLS2yaobxExXGp1QYkyHHVGAjHBUWyKLwzP9bUGA9X/UPaiwBuG5R\n" +
                "oVLut1XaFfHB4x3rskhfP1/124Q0cFKMz7Jz4aRMxWmRjiuzyfDDVGAjGBUmyaLw4lVz8ywrK31D\n" +
                "MqYsCvzolGKrVRLPniwIXzjGvL7eqL50RcXQzDkUXDYjW7BIwvTJgnBxqmUxGTpMBTZCUSHLKosv\n" +
                "XTQ503reIC2vj+OzyXxvaZFVFLhXhZI+DjljrN8WyrAnPuYrXmyyyLUn5VksknB3HMtfk2GCqcBG\n" +
                "IAfSdp6Ynufwf2lWbkIWcTMLnKyo9MlOi/TAx19zWaRPLC71OBIx72BYMsZLkcfikkXh2lTLYjI0\n" +
                "mApsBGKRhNUuizTze0uLlET6oL48J1cGFqpw5uHP64axaFp+2ukvBOCqeXlWUeBmFdJnfWsyYEwF\n" +
                "NsJQ4WTd4PofnVJscQ6gRE1/cFkkvjwn12pXxHtVkA/MnxOMGpnjs9JzlTY938n4LLtsWmEjA1OB\n" +
                "pSEqyCosVuFKFb6mwnkqZMdxnt0miw9/fkaONC5JCmRVpQ+fTc4RBeHSA09NyXcrwVQFrsbD52fm\n" +
                "WEWB60xf2PDHVGBphArWWYr4fYsktOe7Lc8sLvPcvbzc+/OxftufJVGonWuRXlHhpGOdb5PF1YUe\n" +
                "S9anp2YmTWZJFLh8Vo7NKgu3HrDCxo3JsKV1gPSMfCelPqski8KXUy2LyeBI35/JUYYKY5wW6blS\n" +
                "n7Xk6yfnWyszj4xebwtGeeyjFv62qTkii8Kj3WHtigC0H3b+RFkUPrjn7HL54+cmGsOAzz68vbe2\n" +
                "M3ylVRKnnjMx4ztfnZuXVBn6y5o9Hdz+ek19b0QvMFOMhi+mBZYGqFBuk8V3V1X6xv7vmWVHKS+I\n" +
                "hS58bkYOf76oUpmS6zjXJotbVJh68HWnRfrDeRP9UrKVF8Q6b1w6PdvuUMTVFlko70+aUqpYWOrG\n" +
                "Lote4OxUy2IycNLa1B8NqOByKOLaM8dn5F41L08UTrBtaFdElo/1SooouDbV93zBb/B2NqhWSfz6\n" +
                "T04rkRUpNUZ1WYaVhz9sdnSH9dKV43zWMRmpy3+MB0EQ0EHe0hQcs18z7k+1PCYDw7TAUoxDEX83\n" +
                "Pste8JW5uXF/FgLwqSlZ3LCkyGaRhKetsvjAFbNzLA4ldR+nLAqcr/otumF4FHF43FZnjMsgGNVn\n" +
                "q1CRallMBsbwuNNGKCqs1AzOv3FJkUUcQMDWkjIPNy8rtha4lawzxmUkQML+cdZ4vyAKgpFKRdof\n" +
                "PFaJRaUeXZGEK1Mti8nAMJeQKUIF2a6IL315Tm7GjALngMcp9lo5daxXsMqpVxoORWRna1Ao81kp\n" +
                "9g5t045E4bZK0su72if7De5oNJ35w47U3/WjFFEQvuC1SllnjR+85ZTogNX+cOa4DCKakWox4mZG\n" +
                "gROnRbIAp6VaFpP+kz53/ihCBcmmiE9cPS/fNzYFu4aJpMBtoS0YJRGdhxKBAHSENHlnS9BZqxt/\n" +
                "T7U8Jv3DtMBSwzkORfQtHeNJtRxDjiDAtPyBL4lTwekVPiGsGWeq4Eq1LCb9w1RgKcBtla67cFKm\n" +
                "TUrjdJvBMNyuqtRnpcBtCQNnpVoWk/6RPgWbRgkqFPdE9Nmr0mDXsL+09kZp7o3S0hOlI6TRFdbo\n" +
                "DmtE9ZjPSzNAEsAui0iigNMiYZdFsp0yfodCpl0mVXFqJ2JFhc/5lw8aLyOi/y3VspjEj6nAkows\n" +
                "ChfPLHDqHquUtv7Hmo4wO1qC7G4NsasjRE1HmNq2EBHNwO+2kJVhw+e24HIquH0OLJb/GvJRoKUn\n" +
                "iq4bdHaF6e6N0tgapKktSE9Iw2uXKfRZKfVaKfNYKPVZGeu3kelI7a24sMwt/P69htNUcAWgK6XC\n" +
                "mMSNqcCSjF0Rv3R6hS9tcm0MA3a0BHmvtov3G4N8VN9DKKozrsRDRZmXhTPzKc5zUpTnJDfTzkDi\n" +
                "1Q4SDGk0tPSyZ38Xu6s72bWvg5c+amVPbRcZDoXJuQ4mZ9mYnOugPMNGMuvpl3it5DqV8P7O8Arg\n" +
                "X8mb2WQwpKc9P0JRYawiCVsevWSCbE9hsGdvRGdddRdv7+/iP3s7EUSBOZOzmTU5mymVGYwt9iAm\n" +
                "0T8Xieps3d3Oxq3NvL+lmfe3tmBoOvNK3JxU4GROoSspoSL3ra/n0Y9a/vpuRL8k4ZOZDAmmAksi\n" +
                "U0Th2ycXu2/50SnFSY8xiGgGb+/r5OWqDv6zt5PyAjcL5+SzeFYe40q9SbV2ToRhwLaqdt7YUMcb\n" +
                "6+vYtredyQUulpW4WVLmwW1NjDLbVN/Dd57d0x7WjIwADJ9gtlFMGt22I5+TbdK6K2fnzkmmA393\n" +
                "a4intrfy4s52sjNsrFxcwiknFVCUO3xCHVo7Qrz+bh1Pr9nH5h2tnFTq5tQyD/OK3EO6KaAbBmc/\n" +
                "tCXcG9EXBGD9kA1skjBMBZYkVLBLotD5t0+OkxLtsDYMeHNvB49sbWNrYw+rFhZz7vJSJpYP/zLw\n" +
                "9c29PPdmNU+9upfW9hBnjc/g7HE+sp1D41b88SvVkTVVHT/dpBs3D8mAJgnFVGBJQoUVhR7LI3+6\n" +
                "sDJh3S6iusFzO9r4R6AFXRT41BkVnL20BGcatTcbSj7Y1sLfn9nJa+vrmF/m4fzxGUzJHdzb++LO\n" +
                "du56u3brO2FtwhCJaZJARuadnYYokrDy5GJ3QvKGNN3gme1tPLSpCZ/Xxlc+O5lT5hUk1RGfCqaO\n" +
                "8zN1nJ/GliCPvLSHH76wm0K3hc9OzmRu0cCC6ucWueiJaJUq5AagfohFNhli0jYWaaRRZpF+cYGa\n" +
                "mVPiG7oqDQax0sg3v1LN7qDG1y6bwjcvm8LYYg8nKow4knDaZWZPyuKTK8oJC/B/L1axZk8nWXaZ\n" +
                "Qk//9kusssjrVZ29rb3RDxphc4JENhkiRs9dnkJUsImC0P3wxeNEn21ojN6tTb3c8049jSGNqy5W\n" +
                "WbGgaFAxWiOJUFjj3y9V8ecntpNlk/jqrBzUnPiXlvetr+ffgZYHN0T1zyVQTJMhwLTAkkA2nJTp\n" +
                "UC65dHr2oD3NnSGNe9c3cO+6es5dUc6Pr53NhDG+UWVxnQhZEplcmcGFp5fTo+nc8dRutreGGOe3\n" +
                "xRWCoRvw2p6O3FrduDMJ4poMAjOZOwkIAgun5g1+6/HV3e184dGddNgV/n7ncr5w/jisaVQLLN2w\n" +
                "KCKXnl3Jv+46lawxPi5/dCe/fbeBYPT4dQun5joIRY0cFYqSJKrJADHv/iRQbpVuOr3CN35i9sD6\n" +
                "qLYFo9zy+n6e3dXBdVdM58qLJuAeBp1/0gW7VWbBjDyWzSvkyfV1PLiujmKPlaJj+MdkUeCtfZ29\n" +
                "zT3RzY3wQZLFNekHpgJLAvmicMcnp2S5cl39Vzpv7e3kuhf2UTHez/9cfzLjy7wJkHB04HNbOHNJ\n" +
                "CT6vjTuf3MnW5iBTch30ldbV0B1RdjQHo/t14+EUiGoSJ+YSMsGo4O6N6Llj/f2LoAhpOne+XcvP\n" +
                "367l+i9PZ/XVs0yra4g4Y3Ex//zlqTgL3Hz+3zt5cVf7UcfMiBVlXJJ04Uz6hanAEs80v0MO9qdT\n" +
                "T1VbiKue2kM9An+9YznL5xYkULzRiddl4QdXzeTWb87ht+818uM1NXSGtEOvT8iy0xvRc1TISaGY\n" +
                "JifAVGCJZ1plpj3uLcIXd7Vz9VO7OXVJKb/63gKyfCOrZn66cdLUHP56x3IEv50vPb6LDfu7gVgD\n" +
                "4WKvtQc4KbUSmhwP0weWYEoU8UsLSz3zZpygTrymG/zf+gb+EWjmZ9+ax1lLS8zQiCRhs0icenIh\n" +
                "Pq+NWx/dTm9EZ3qegz1tYXl3a3B/vcELqZbRpG/MVKIEo4jCzGLv8aPB24Maq9dUE5Il/njbUvKz\n" +
                "EpYuaXIczlxczNRxfr5753/46MV9LCp2iTZZPAVNO/HJJinBXEImmIhujC07zjJwb3uIq5/eTV6p\n" +
                "l/t+tNhUXimmOM/J73+yhLxSLw990ERPRJ+imj/0aYu5hEwgKrijuvGjr87N67Nu1bv7u7nuhb1c\n" +
                "tKqCb142BTlNG16MNmRZZOncAhwOhf9sahABqcHg5VTLZXI0pgJLINkwwWWRvnjZjOyjfsFf3NXO\n" +
                "bWtquOnLM7jw9DFpVRHVJMbEch9zJmfz4tr98/NEITNDM55vNCu1phXmEjKxlOS6lKMcKP/8sJn/\n" +
                "XVvH/1x/EqedXJgKuUziZEqln4duWypn+mxfcdrlp1Uw1/hphKnAEktZkcdyRPTpfRsa+OeWVn67\n" +
                "ehEzJ2alSi6TflCQ4+DBW5dYx5f5ljns8rtmbFj6YCqwBOJQxAkFBxSYAdy1to41Nd38dvUiKko8\n" +
                "KZbOpD+4HAq/uulky/K5BZV2q7RRhZJUy2RiKrCEYpXFiXkuBd0wuOOtWja2BPnNDxcOq4YaJv9F\n" +
                "lkS+/+UZ0nnLy3JtVuk9FcanWqbRjqnAEoimG6XZLgt3vl3Hts4Iv129iBz/wCpSmKQHggDfvGyy\n" +
                "+LlzKjMsirhehZmplmk0Y+5CJpAsuDUKll3dUe69eQEZnqErJ22SWmZOzBJcDsWy/sOmz/l148VG\n" +
                "qEm1TKMRU4ElCBXskiyujsgiv/7hQlN5jUAmVWTgdVnkdZsaP5OpG882Qm2qZRptmAosQRRZpHu8\n" +
                "Lsus365eZCZkj2DUsRn43BZl/YdNl/g148lGaEi1TKMJU4ElgOmK+B2bVfrW/asXSwXZZtjQSEcd\n" +
                "m4Eii5aNW5ov9eumEksmpgIbYqZK4qWyJNx13+pFyphCd6rFMUkS08b7kURR2bS95ZIMzfhbIxxd\n" +
                "JdFkyDEV2BCiwlJJEh7+3xvmK5PGZqRaHJMkM31CptDaEbLsqu680BfVH2yE3lTLNNIxFdgQocIk\n" +
                "iyKuufmrM6wLZ+alWhyTFHHS1Fxh2552R31L7xkHlFgk1TKNZEwFNgSokGW3Susuv2CC58LTxphp\n" +
                "2aMYQYAls/Ol/3zQ6O/sjpzki+p/NRPAE4epwAaJChanXX510cz8sv932WTz/TRBEgWWzc2XXni7\n" +
                "pigS0fP2R/WnUy3TSMX8wg2SMrv8YHGec8md3znJIpn1vEwOYFEkFs3Kk//9UtW0TJ2aesPYmGqZ\n" +
                "RiJmKtEgmCqJX1Vk8cL/+e7JVks/ug6ZjA7ysxzc+e15FkHg16rZHCQhmBbYAFFhriwL/7z7hvmW\n" +
                "skJXqsUxSVPysx1keKzS+kDTRRma8adG6Ey1TCMJU4ENABWybFbp7WsvmexaNjc/1eKYpDkTy33U\n" +
                "NfUqe+u6Vvqi+v2NoKdappGCue7pJyoILofyr8Wz8ryfXDEm1eKYDBO+/fkpUmGOs9Jhk3+RallG\n" +
                "EqYC6ycWRbzO5ZDn3fil6WanGpO4UWSRn/+/uVbDMK5U4exUyzNSMJeQ/UCFOaIoPHTPTQss+WaO\n" +
                "o0k/cTsVxhS6pdfW157r142HzHSjwWMqsDhRwWW3Sm9effEk39I5pt/LZGCUFbppbguJe/Z3nWr6\n" +
                "wwaPuYSME4dd/tXE8oysT60sT7UoJsOcr392spTjt4+3KOJNqZZluGMqsDhQ4QwMLvnxNbMsZv9G\n" +
                "k8FiUUR+cu0sK3CTCtNTLc9wxlxCngAVfDar9NqNV0x3Th3vT7U4JiOETJ8NQRDEwK62031R/TeN\n" +
                "cFT/UJMTY1pgJ8Bpl389S81yrlhQlGpRTEYYnzunUijOdRbardKtqZZluGIqsOOgwum6blxw05XT\n" +
                "LamWxWTkIYoCt3xttlXTja+pMCvV8gxHzCXkMVDBYbdKa77zhanu6RMyUy2OyQjF67YgCIIQ2NW2\n" +
                "xBfVf22W3ukfpgV2DOxW6cfjyryeMxebDZhNEsulZ1cImV7rGFkSr021LMMNU4H1gQpqVDO+dtMV\n" +
                "063mrqNJojnQ8dsqCNymQmGq5RlOmAqsD5x2+cFLzhwrlZlNOUySxPQJmaxcWCQ77fJ9qZZlOGEq\n" +
                "sI+hwqesFmny5eePN20vk6Ty9c9Mkg2DU1VYlmpZhgumE/8wVLDbrdLz3/3iVM+EMb5Ui2MyhAg2\n" +
                "J4IzA9GVgeDyg92JYLGDroEeTbV4AFgtEjarLH2wrWWBL6rfYzr0T4ypwA6j2CrdXF7kXv6ty6bK\n" +
                "pu9reCNYHYj5FUiVJ6OVzuDt7RF2dlcQzluOUbiMZssUPqix8+K7dbRaciiaNgNFkTGCXTGlliIm\n" +
                "lHt57OW9jp5gtLbB4L2UCTJMML+mB1AhT5HFqvtXL7JMLDetr+GK4M1BGjMLwZtFRPfwlwde5LVn\n" +
                "X+fWPz5ISUXFUcdrmsa9q1fz4C/u5NOXn8MXrzoTR7gFbfd6jJ6OFFwBrHm3ju/dvb4tGNKKA9CV\n" +
                "EiGGCaYFdoBSu3zXopl50z69qjz174kgIDh9iN5sBE82gi8PwZ2F4PCAYgFdBy09lj3pgmBzIU8+\n" +
                "BalsOnL2bLbvsfKlsy5nw1vr+f0rr5Bf0nc4jCiKzFu2jK72Dh74xW948pG1TF6ygsKZ0xEcGRjt\n" +
                "dUm3yEoLXLy9sUFo7QwrdZrxUlInH2aYFhigQoUiix/9/Y7lclGuMzVCKDbE3DGQXcH7W1qIyqXk\n" +
                "l6k4PV7aW1p4f+3bvPLoP/FniCw5fRYLFk9B6mpEr92G0VYLxuh1l4iFE5DGzkFyVSC5VNa+soar\n" +
                "zz6bYG8vt/7xj5x72WUnHCMSDrOqspLavXuRFYVbHvgtK1dVovfuJ/rRqxjNNUm4kv+ydU87X/z+\n" +
                "mnAkqhcGoCmpkw8jUm9tpAFldvkPZy0pGbdqUXHSd2UFhxd5wiKkynm8+k4bf//HJs778k1UTJmJ\n" +
                "PycHl9dLVl4ek2bPZsUnL+btV9Zx5/fv4vF/rcVVOImJSxciF08EQ8fobgVjFJWXEiVkdSli8VSU\n" +
                "rCWIjjF8uGEjV5x2GsHeXpxuNz998EEk6cS3uSRJtLe0sH7NGnRd55XHn2TG8k9SMnEmeKwgSrEf\n" +
                "iiSR5bOxeUer1tQatNVqxnNJm3iYMeotMBVURRbff+zu0+Qsny15E8sKUsVJiLllhIx8fvD1O6nf\n" +
                "X8cfX30VWVGOe+r1l17KEw89BMCcpUv5+YN347PWYoRb0batRW/YlYwrSC2ijDx9JWJGGXLmIgTR\n" +
                "Sm93N+dNnUr1rtj1T58/nz+/+WbcQ77y+ONcc+65hx77MjN5IhAgw28n2vQaWt1WtC2vD/mlHIsD\n" +
                "VlgoEtVLAtCQtImHEaM+Dsxpl396wallYjKVl+DNQTnpIuTiuYSsC7ni/G/y3MOPcPO9955QeQHc\n" +
                "cNddOFyxVm7vvPoqn1l8Ni3hichZC5EmLkWevgqUJCrjZCOIyNNXIGaMQclahiBaAbj/pz89pLwA\n" +
                "XB5Pv4a1O490H7Q1N/OL669HkN3I2acg5U9EmrBg8PLHyfgyL4tn5YkOm7w6aZMOM0a1AlNBDUf0\n" +
                "VZ87tzJp74NYMA552kpk/1wE33y+8cnPsGndOtSZMxk/bVpcY3j9fpYfZinU7NnD5aeeSm/EjSVv\n" +
                "FVLOVJS558finUYg0vgFiL5SlKylIMSWh92dnfz57ruPOG5/VVW/xq2vrj7quccffJD66moEyY6c\n" +
                "vRwxdzxi8eQBy95frrxwghKO6JerYNYx74NRrcCcdvm2ZFpfYulUpMr5KNlLER1l/P7nP2ftS7FN\n" +
                "JnXmzH6NNXHGjCMe7966lVuuuQYEBdl/MpJ/JvLMMxB8eUMmfzog5o1FzK1Azlp8SHkBvPToo3S2\n" +
                "H9kjY9dHH1G9e3fcY695+umjntM07dByXRBtKFmLkcpnIniyB3gF/WNMkZsFM3INu1X6VlImHGaM\n" +
                "WgWmQnkorJ9x2dnJsb7EgglIZTNQspcjKH4aa2v5vx//+NDr8SwdD8fl9R713GMPPsj7a9cCIDkr\n" +
                "kTPmIk89DcGTNTjh0wTB6kQadzKy/2QE8cgfnTef69vP/evD3uPjsX3zZl545JE+X3vjsLEFxYfs\n" +
                "nY48+RSQktNZ7wvnjbNounG1CmYL+I8xahWYwy5/b8WCQrL9ibe+BF8uUsVclKwlCHLML/O3e+8l\n" +
                "2NNz6Jh9u/rneK85hmXxu9tvP/S3aC9B9s1CnrYCwZai8JAhRBo/H9ExBtGac9RrH73Xd9D6v3//\n" +
                "ex6+//7jjtvS0MA3L7oILdp3bN2Wj40tOsoR7NlIpckpZ6+O9TGuzIskCV9OyoTDiFGpwFTIDof1\n" +
                "z37u3HGJ/wlVbMiTT0HOmIWg/Ncn9eRf/nLEYRveeOOoJdDxePWJJ/p+/skn6WhtPfRYdJQhucch\n" +
                "TVsBwvD9uAVPFkJGPrKnb/9Tw/79xzz3h1deyS3XXktbc/NRr736xBN8au5cdm/ZcszzO9vb6e3u\n" +
                "PuI52TcbsWgigjU5/UG/eN44m0UWr1fBbKh8GMP3jh4EFkW8Zt6UbKOsIPEWuTzuZERHEaK99NBz\n" +
                "+3buPGK3DKC3u5v7brstrjFf+Ne/2LZpU5+vadEo61555YjnJM9URGceUvnwrVoslc9Bck2AAzuO\n" +
                "HyfU23vMcw3D4C+/+hVL8vP5/LJl3HDZZXz9ggs4paSEq885Jy5nfzQSOeKxoHgRbQWIJVP7dyED\n" +
                "ZMGMPPxemwv4ZFImHCaMOgWmgiKJwtcuOXNswuvcC94chKxiJN+RiuNYyucPd9zBM3//+3HH3LZp\n" +
                "Ez+48srjHvPRxo0flwTZfzJi4QQEx9G+s3RHsLsRvFmIzrHHPOZgWMnxiEYivPPqqzz+pz/x4r//\n" +
                "Td2+fXHLoFiPVpySeyJifkVSfGGCAJeeU2FzO5QbEj7ZMGLUKTDgokyfzTZ7UuJ3kaTKk5Dc6qE4\n" +
                "pYM01vYd0a1pGtd95jP89BvfoLm+/ojXQsEgf777bj4zfz7tLS3Hnbepru6o5wTZjeSqRBqfvDim\n" +
                "oULIHYtoLTjqfTyc4rHHVm6DJbewEJvdfrRcig9BciFmJafs+KqFxYQi2ngVpiRlwmHAqFtPux3K\n" +
                "DZ89q8KW6HI5gjsLwZmB5OyjAsIxnMUQU2J/uusu/nrvvYyfNo28oiI629vZ/M479HTFV5ggEg73\n" +
                "+bzkmojm2YHgzsToPNoflK5I+eMQHWXHPWbqvHlsWrcuIfNPmTv3mK+JrgrEgn3o9YnPfrBbJc5a\n" +
                "XCI++1b1N+iNXp7wCYcBo8oCU2FKKKJNWLkw8T0exeLJSM5yEI7+jfD6TxxgGo1E+HD9el569FHW\n" +
                "vfJK3MoLjrOcEhUkZwVS2Yy+X09DBKsDLNY+dx4PZ9k55yRMhuONLVrzENwZsTVeEvjEaWVSJKJ/\n" +
                "RoXhv608BIwqBeawyVevXFiMw5Zgw1OUELOKEB1j+nx5zPjxCZ2+r7pXBxGdFQgZ+SD3L+4sVQje\n" +
                "HATRdUTQal/MW748IctIr9/P6RdeeMzXBdkFgozgTk7rvcpSL2OLPYYoChcnZcI0Z9QoMBVsUU2/\n" +
                "9MLTyhK+bBZ8uSBYD8V8fZxxU6fG5XQeKDPmzz/ma4LkQJDdSfPbDBbBnYNoP3E2gSiKfC3OoNX+\n" +
                "cPl1153wsxIVf1LTti46fYzNaZe/mbQJ05hRo8CA8wuyHWIyat2L/iIkR/ExX1csFpaedVZC5s4t\n" +
                "LGTS7NnHPUZ0liHmJM7pPZQI7kwEOb7uUGdcfDGLVq0asrknzZrF5755Yj0hWP0IruQ1Pz5lXgG9\n" +
                "QW2cCsPjQ0wgo0aBuZ3KVeedUpaUpEfRV4BgOf4Nfck11yRk7k999auI4vE/VtGSg+BNTi7fYBFs\n" +
                "LpDid/fc/tBDjJkwYdDzZuXl8ct//SuuFC9BciI4kleG3GGXWTwrz7Ao4qVJmzRNGRUKTIWcnmD0\n" +
                "5BXzE++8B2Idb5Tjx1vNWLCApWefPaTTZvBIZ5oAACAASURBVOXlcenXv37C4wTFC6KIYB0GfmBR\n" +
                "RhDj99d5/X5+9+KLjJs68ADT/JIS/vDqqxSUlp74YABRAfnYIR6J4IzFxYosi19K6qRpyKhQYKIg\n" +
                "XDJ9QmY005eEm0ySY8ohDqvh+/fcE9eOZLys/u1v4/StCQiCHVzDoHmJINDf2zS3sJC/vPUWF15x\n" +
                "Rb+nO/X883l4w4b+bbQICkKSErsPMn96DoZuZKswfNMrhoBRocCcdvmKs5eUJOUnUlBsgHDCXTOA\n" +
                "vOJi7nrkkT6DJPvLNatX98uiExQPgnXkFjewO52s/u1v+cf69ay46CIUy7ETL0RRZPEZZ/CHV17h\n" +
                "rkcewZfZT3+WoSW98YcsiaxYUCQ5bPIXkjpxmjHiA1lVKOwJRccvnpWkuliyQn9+F+YsWcKvn3mG\n" +
                "a887j862tn5PJwgCX/vJT7jyxhv7d57sBCW5y54BoUcxjMiAa59PmjWLX/zjH3R1dPDu66+zMxA4\n" +
                "lAmRkZXFWFVl1qJF/Vdah2NoGCnoJXnKSYXS82/XXAgkxqE6DBjxCkwUhE/MVrOiLoeSpAYm/f+q\n" +
                "zVmyhEc2buQHV17JW88/H/d5BaWlrL7vPuafdlq/50S0IFiSU0lhMBiRMGjBQY/j8nhYcuaZLDnz\n" +
                "zCGQ6kgMPQihnhMfOMTMUjPRNMOvwqQAfJh0AdKAEb+EdDrky087uTB5poauMZCO8AWlpdz33HPc\n" +
                "/8ILLDvnnOMueSbNns0Pf/Mbnt62bWDK6yDDoIOR0d2CoaV3b1cj2oXRlfzULFkSWTgj11Bk8YKk\n" +
                "T54mjGgLTIXM7t7o5CVzkldO3IiEAJ2YEuu/NXbyqady8qmnEuzpIbBhA/t27aK9pQWH00luURGT\n" +
                "Zs3Cn3P8tJr4BI1CNDT4cRKM0dmIEWpJ61qkRqgRo6f1xAcmgKVz8y3rNjd+mqg+9FG8w4ARrcCA\n" +
                "VePLvCGvyzJ4L3m8RIJggKH1IkgDX6LZHA5mLlzIzIULh1C4/2JEujGifSd9pxNGRyN6uDHVYhwb\n" +
                "Q8fQOjE6UiPj/Om5dPdGx6uQF4Cjy5CMcEb0EtLtUC5aOic/ecrrIOEgRrQj6dP2ByPaAcH0XpoB\n" +
                "saoZRhQj2plqUfrEiLRAVMMIdp/44ATgdihMrsgIAytTIkCKGbEKTAUpFNFOXTgjN+lz6x2NGJH+\n" +
                "7ygmE0PvwehJbyULgGFgtNahB49ueZYO6L170Rvj73yUCOZPz7W7HUpictPSnBGrwIDZdqssVZYm\n" +
                "vwKp0bwXvSf+ap/Jxoh2xpY+w0GBAfr+LWhdO1ItxtEYOlpPFXrd9pSKMXdyNlFNX55SIVLEiFVg\n" +
                "kiScOm9qdkquz2irw9A6QI+c+OAUYIQbMDpbGchuaSrQW2og0oMe6ruSbarQg/sg1I3R0ZRSOSaW\n" +
                "+9B0w61CZUoFSQEjVoE57cq586bkpKTolRHqwejuTNtlj9ZdhV6fWquhXxgGWtUHaO199xJIDQZa\n" +
                "+2a03X23c0smkiQwY0JmFDgl1bIkmxGpwFSwdvdGps+elLqGrnrNZrTObSmb/1gYWhAj2orelL5L\n" +
                "3L7Q92/BCLWi96bHj4Leswcj2IbekPhS0vFw8rRcm8dlOTfVciSbEanAgFlel0XLz05dpLlevxsj\n" +
                "2oERTu3y4uPovbsw2uohfOw2ZGmJrqFtX0u0bUPKl+aGHiTavpHotjfBSI9l+LQJfqJRfV6q5Ug2\n" +
                "I1KBiYKwYMaEzNRem66h7d1MtP3jLc5SiB5B69yKtntDqiUZEHrDboy2GqJt61MqR7R1HXpjFUbL\n" +
                "sZvpJpvKEi+hiOZVIXlR2wlGBasKPhWO6QoakQrM5VROnzExK+F9H0+Evm8zRrA5bXxhWtdWjI6m\n" +
                "lDudB0M08Bp6z1607tTsSmqdAYyuarStb6Zk/mNhUUTKCty9wLFbKKU5KogqnKXCX1TYCwSBViCs\n" +
                "wlYVfqnCuMPPGZEKLBzR5kypzEi1GDErbNubRFvXg57atB0j2onWtRVtW3p98fpNJEh043No7e+j\n" +
                "B5NrAem9e9E6AkTffxa09NthnjEh025RxGM3REhjVDgD2Aw8AVwMFANkOmQq/DbGZ9nH+Wzy14HN\n" +
                "KtyoHsjTG3GpRCoURqK6a1wK4r/6Qm/ci9i8j4iyFiVrMQPJjxw0hk605S306gBGd3oH2MaD0dVC\n" +
                "9IMXYCrIGXMQ7YlvUKL3VhFtfYfo+8+mbfzc5MoM8YW3a5YRSf8UsYOoYAXuBb4IsfqV84rcnFLu\n" +
                "ZWaBkwz7kSrq3f3dyi/e3H9LbWfYDdxwlAJToQJYFfsTN9ADbAfWAOsC6R88NLM03xWUJCFt6iVH\n" +
                "P1qD4vKjWTYjeZLfVDnavgG9Y/+w9X31hdFWd0CJGUjRLiS3mqiZ0Do2oXVuiymvNF5+q2Mz6AlG\n" +
                "J6dajnhRwQ88DiwQBDhjXAaXTM0i331s78+sAic/OqWYKx7deb0Kj8mHDVYO/BI4XlnPPSrcDjwQ\n" +
                "gLRU85IkzJpckZGU5h1xo0WIbnwGZltAtCC5EtsX8oipOz9C79yJtumFtNkxGyqMtjqi65+AaWH0\n" +
                "UD1yxty4SnnHPX60i2jrWozuOqIbn8VI89zRkjwnumFYVCgMQE2q5TkeKmQDLwOTvTaJHywrZnp+\n" +
                "fJ/dWL+NUp+VqrbQN+QDg80Bnjm52J15ylgvhZ6YBqzvirCjOciG2i4+auzFMCgD/g/4hgqXBuCd\n" +
                "RFzcYHDZlYUTy31JKl4YP0awi+h7T8FMwIgiuSclfE6tM4DW8SHR957GSEHBvWRg9LQTWfcI0tg5\n" +
                "GJFWJNcEJGdlrNHGQNEjaF0foXVtR6/dhrZzfdJLRg8EURQoyHYE99V1q6SxAlPBCzwDTC71Wbnt\n" +
                "9FLyXP37vDLsMlVtoSWyCj7g31fMzs28eOqRgZ/js+wsLvPwRXLY3xnm8Y9aeGpbG91hbTzwpgpX\n" +
                "BOCPQ3VhQ0FE0ydXlKSH/+vjGN1tMYthRhQj3IbsnwtCApIFDI1o23r0rt1ENzyF0ZuelRyGDC2K\n" +
                "tu1t9P1bMSqb0TxbkBxliI5yBCX+xiVGpBWtexd6bxVGeyPa9reHnc+wssSr7KvrngS8kGpZ+kIF\n" +
                "B/AkMGtitp3bTi/FY+2/vdHaGwXIk4FrPVap8KLJx68JXuC28JW5eVw8NZu73q7l1d3tCvAHFfID\n" +
                "8NP+X8rQo4KlNxjNGVOYvtXvjN4OIu88iqwuQ480I2fMQ7QOXcUMI9xMtGUtekct0U0vxuqTjRKM\n" +
                "rhai7z2F4PKjF6mI2btAlBGtuQgWP4LsipXSFmQMIwp6OFZNNdyMHmoAPYLesAe9+sNhp7gOUlHi\n" +
                "sbyzuXEmPem3S6qCBfgXsHBGvpNbTyvBKvc/ECIU1anpCAOEZODCYq8VWYxvd8xrk7h5WREVfhu/\n" +
                "e7ceA25TQQvAz/stydAzzmGTwx6XJb27VURCRN9/FjG/EipCiNZcJO8UBGXgoR9GtAut80P03mq0\n" +
                "3RvQqwMJ93kZIR2jXcPo1DCCGoQNjMiBOUUQFBFsAoJDQvRICB45KZuwRlcL2pY30La+Gevs7c1F\n" +
                "8OQgOnyxpiuiBLqGEQlj9LZjtNdhtDdgdLaQ/ntUx2dssQdRFKanWo6Pc8Dy+huwcn6Jm5uXFWOR\n" +
                "BnYzbNjfTVQ3ALbLwARjAB/aJdOyyPco3PZaDVHd+JkKnQH49YAkGjrGl+S7osS2ZtMevXY7emMV\n" +
                "Uuk09MIGBNmL5KpAtBWAGMclGBH0UD1a146YFdGwB333uwn1d+nNEfT9YfSGCEZ3P/1CkoDolxHz\n" +
                "LIiFVgRLgrWZYRwWuPsh6e/FGjylBS6CYW1MquU4HBVygEeBk5eXe7l+cWHcBlNfPL/jkHX8kgwo\n" +
                "B8yxfrNsjBePVeZ7L+4lFNXvOaDE/jxgyQbPuMoST3rtQJ6IaBht5ztoVRsRs8vQC2sQnG4EwYpg\n" +
                "yUKweBFECwhyrDKpHsGIdmIEmzCMHgj2otVuQa/dkbjlom6g7Q2h7Qz2X2kdjmagN0bQGyPwYTdi\n" +
                "vhV5nB3BnXZ7LsOW/GwHobDmUsEWiEWypxQ1ViHjQaDgE5MyuWpe3qCM8JbeKG/uPeTTfVAG6tqD\n" +
                "Wv7OliBj/f3/7s8qcPKzFaXc+MJesTusPagCqVJibocysSDHOTy/DdFIzCKr3Q6SjODNQXD4EFyZ\n" +
                "CBZ7rOO3pmFEejF62qCrBb2jKeE+Lr0uTHRzN0bPEHcw0kGvCRGuCSGVWJFUZ+ItslGA3SrhsMnh\n" +
                "nmC0FNiaKjlUKCEWcvVpURC4al4uF6iD6L15gEc/ajm4fHw1ABtkYC1w/htVHQNSYABTch3csbKU\n" +
                "G57fK7YFow+qYA3AA4OWtp+IolCZn538EvhDjhbFaNmf2mRhHaKbutCqEp8Cpe0NoTdEkGe7Ef0j\n" +
                "Ljkk6WT7bZGq/V0lpECBqbH+Ud8FvgPYMh0yNy0pijvG63i0BaP868ND7eu+C7FcyMcAntnWhj4I\n" +
                "p+/4LDu/PLOMbKciAr9T4Y4Duw5JI6rpJaksoTNi0AwiazuSorwOYgR1Im+1o9elZXz0sKIg2yES\n" +
                "s4CShgpuFa4H9gDfB2xLx3h54PyKIVFeAH/a2EhvRAf448EYVJHYzkBjQ3eEt/YOLtK4xGvl3rPL\n" +
                "qYhZct8C/qNC0pJLQ2E9OzdzBFhgKSayvgu9KQXb8DpE1neit0aTP/cIoijXaZMkoSwZc6lQqcKd\n" +
                "QDVwG5BZlmHl5ytKuXlZEe4BxHj1xdamXh77qBWgnphuAUAMQOjAxPxpY8OgJ8p0yPzvWWNYOsYL\n" +
                "MJ1YwOtf1QT/IqjgjGq6kukdXj78dEPbHUSvT6EVpEN0fSdowzucIZXkZtoFh1VO2E6kCjkqfEWF\n" +
                "14BtwP8DPJWZNr63tIjfnV/BrCGMxQxGdW55tfrgCvHLATi0jjzocLgH+Or25mDly7vaWV4+uEh2\n" +
                "myxy87Iipuc7+M079fRG9E8D56lwF3BHABKREZtns0oRizKYHJJRjmYQ3Zr6lCOjV0fbHUSqMK3p\n" +
                "geBxWRBFYQjat8dQwQnMAxYR6z85lwOluBRJYH6xmwsmZTIld+jdN4YBt7xaTXUsUuLOwAGX10Fk\n" +
                "gECsYNg1wHP3ra9nfokb2wAiZD/OORP8zC1yc/faWt7e22kDrgOuUeF+4H8DMJQFxXM8TkuY41Rv\n" +
                "NDk+em0YwgOzfHojYYLhMFFdR9M1ZFFCliTsFgtWuf8fiVYVMhXYAPG4FASB4zaEUMFOLI3Qe+D/\n" +
                "g3/7gTxisVtlwASglMNCkK2SyMwCJ0vLPSwo8eBQEldW8Bdv7j8YNvECMR/bERza8gnA8yrcX98V\n" +
                "+dKv19XzjflDU5k2z6Vwy6klbNjfzQMb6gk09DqBrwNfU+E5YruVTwxBzEpWhiflRViHNXpL/L4n\n" +
                "Tddp6GinqauDtp5uNP3YYRaKJJHhcJHl9pDl9iAKJw6XMLo1jJCOYB2RNTcTisdpQdMM/4GKD3OJ\n" +
                "lcaaRKziTB5QSCwyPi4cikhFpo2puU5mFjiZlONAGWAUfbxEdYNbX6vh1d3tAOuBCwJw1A368T3r\n" +
                "bwLzH9/Sok7Ncwx6KXk4MwuczCwoZ3N9D48EWni9qkPQdGMlMZO0Q41VYnwKeP7wNW4/8PvcZiDR\n" +
                "YDB6TxzrpRsGe5sbqW5pJhpnhYaIptHQ2U5DZztWRaHEn01Bhv/EAY29OpgKrN/43BYiml4E9Mup\n" +
                "7VBEcl0KxV4rpT4rJV4rFZk2SnzWpJbhbO2NsvqVfXxQ1wOwDjgzAH3uMB6hwALQpcIFwLqfv77f\n" +
                "47fLQ7YFepDJuQ4m5zpo6onwyq4OXtrVzramXg/wmQP/UCEAvB0Tia3ElppNAWg8ztBul1MZnkGs\n" +
                "6cIJdEVvJMzm6iq6QwMPrwhFImyv309jZzuTi0qQxeN8ZAn+lR+peFwKkah+xBtrlUTy3ApZDhm/\n" +
                "QyHbKZPrtJDjiv2d41RwWVL/9fmgroefvFpNUywZ/WVillf7sY7v8w5RYRnwnF0RlR+dUsKsgsQW\n" +
                "N63tDPOf6i427O/m/bpuOkPH/GXXgBZiF9R22L92oOK85aWLb7xiunnXD5Dolh60bX23W4toGu/u\n" +
                "2UEwMnThFR67g+klY/peUopgXeU3ldgA6OyJcMrlT/OlWblMyLZT6rOS6UjvAOFQVOeBDQ08/GHz\n" +
                "wRoE9wLfCMBxb7g+ryoAr6hwUW9E/+cNz1cp311UyKljE1djK99t4byJfs6b6McwYHdbkG1NQT5q\n" +
                "7GVnSy9VbWG6wxqARGxdn/3xMURRwOUw/feDQcy1HFOB7W6sH1LlBdDR20NNazPF/qP9zWKWYiqv\n" +
                "AWI9sBA5X/VjT6CDfah4dXc7v15XT0N3BGLGyFcCsfjUE3JMtRyAx1T4RFQ3/n7ra9X2rU29fGVO\n" +
                "LtIgssjjQRCgPMNGeYaNlZX/LUbX3BNlX3uIuq4ITd0R6rsjNHZHqO+K/R81YnlgJgNHzJAR/fJR\n" +
                "znzDMKjvSEx9rPr2tj4VmDTW3IEcKJYDSktL4xLiBvDW3k4efK+B7c2H9u8eA64NQNxt449rVwbg\n" +
                "CRWWA//+14fNeZvre7hxSSHF3uRXq8l0yMc1g+/6T10SpRm5yFNdhNe0g/7fm1/T9ePuMg6GYPRo\n" +
                "q04stCJmm9b0YElH/dUR0nhhRxuPbWmhuv2IgOk9wN39UV4QR1u1AKxVYTbw161NvYuueHQnn56S\n" +
                "xSXTsgdckCwR6AZIUvqby+mO4JGQpzuJbvjvpo8kSciiFPeuY3+wK0eGvgheGWVa2jSUGrY4bTLd\n" +
                "EX3IUnkGQ1NPhHdrullT1cH6mi4imoEAzC50cd5EP4HGHh7/qLWsK6y9qMLviC0h44rpicuzF4Ca\n" +
                "A47974Y14wcPbmy0Prujjc/PyOH0Cm9ccT2JJqIb2G2p/7BGAlKRNZbS80EX6LGdnjyfj+qWgUS3\n" +
                "HJ9833+r0IoZMvI8N8ipv5+GO6l6BzXdYF97mF2tQd6v6+G9/V0Ho+gB8Fglzhzn5awJGZQfaB42\n" +
                "v8TN+RMz+fGr+/igrudyoBe4Np754t6aCMR2AG9TYzWtn2voipT97PUa/rSxkQsnZbKi0pfQiFyT\n" +
                "5CKVWBFcEtENnRg9OmOycmnt7qY7NHT1xzKcrkMKTCq3IasOSLCP1WRghDWDnohGT0SnJ6LTGdJo\n" +
                "7Y3S1B2hqSdKY0+EqrYQ1e3hg/W6DpFhl5lX5GJ+iYeTil19VmPNdMjcvqKUrzy2i6q20NUq3B+A\n" +
                "908kV7/2VlU4iVgaUJnPJjPWb2VDbTd3r63lvvX1LBnjYXm5l5n5zoQ7+z+OAEQiifHTjFZEv4xl\n" +
                "mY/ojl7YGWR6SRmbq/fS3jv4fMlst4cJ+UVIfgVpkhMxI723+Ucz66q7uPGFvXGV2xIEKPJYGJdl\n" +
                "Z3KOg0m5DioybXFZhFZJ5PMzclj9yj4BuIzDqk4ci7jvmgOlYZ9QJMH++Rk5fGJSJhZJYF97iH9s\n" +
                "bualne08t72N57a34bZKTMuLpR1Mz3NSlpF4p79DFgmFR0PV8yQjCcjjHcjlduSqIDM8FeyvaaKq\n" +
                "uZHQAMIqHBYrY3JyyR2fhVRmQ8w0nfXpjscqUey1IIkCIrEEbrdVwm2V8FplclwKuU6FAo+FEp8F\n" +
                "6yB80bMKD/k/58VzfFwK7ECVxYcUSbDffnrpEdH5xV4r31pQwFVz83htTwcv7mzj/boe3qjq4I2q\n" +
                "DiD2BpT7Y6ERpT4rZRlWshwyWQ4l4TlVJkOEIiBV2JEq7JR1eiiqLaRpZwsNNS20dXYTjPRdgkcA\n" +
                "7FYrGT43uaWZ+Mf5kXIU08+VYHpCGtYh+m5NyLbz+wsqhmSsE3FYMk1RPMfHa4FdBuRdOCnzmKlF\n" +
                "dkVkZaWPlZU+usMa62q6eKe6i80NPVS3h9lY283G2u6jzvPaJPx2GadFwqGI2BURr1Xm0unZ/Y4e\n" +
                "1tNw23gkIrglFLeD/HEO8imCqEGkI0JPcw/RoIYWjiLZZCx2BXuOA9mZnHZqJv9FN4xhaRxUdxxK\n" +
                "U4vL2RqvhlgJHBFYejycFollY7wsixU1pC0YZXtzkN0tQfa0hajpCNNwwPnXHtRoDx699BufZWPV\n" +
                "uPj7JDplge7e9GvmOSqQBRS/Ba/frAaSDgQPpOJZ4lzKravu4u61tXzj5PwhLUQ4ENbuOxS+syee\n" +
                "4+NVYOOBAQew+mwycwpdzPnYm2MAnSGNzpBGV1gjGI054QWEfhdHc1okmrtMBWZi0hOMIopC3HGa\n" +
                "OU6F7rDOd5+r4pNTsrh8Vs6g+jYOFN0weHxLy8GHb8RzTrwKzAKxKFrPEAbGCcT8Y0MxptMi0tVt\n" +
                "KjATk55gFEc/KkuUZVi55+wxXPd8FX/f1MTG2m5uWlpEUZLr6z28uZnDetT+PZ5z4t0uaAD4sCH1\n" +
                "5YajusG/PmymuefIQF2nItHdYyowE5OeYBRZEvqVSpTvtnDPWeXMLnSxtamXKx/dyZNbWxMn5MfY\n" +
                "1tTLAxsOlS/7dwC2x3NevArsfYDnticmoTde9raHuOrxXdzznzrerztyQ8BpEekyFZiJCW0dYaKG\n" +
                "wc/eqOnXeW6rxO2nl3Lp9GzCmsEv3tzPTS/upS2Y2C5R+zvD3PTiXsKxRi5dxJqExEW8CuwJgNer\n" +
                "OtjW1He5lUTz1NZWvvLYLna2BLl8Vs5R1WJ9dpmm9uT1MTQxSVdaO0NYZZHntrfxhw39KsqKIMAX\n" +
                "ZubwP2eUke+28PbeTr74yE6e35EY4yXQ0Ms1T+w+uKLSgcsCcTrwIVZf64Rkxyqifh7wftTYy6rK\n" +
                "jKRF2neGNG5dU8PfNjUhCQLXLS7k3In+o44TgD9taODyC8aTBqmZJiYpY/3mJlpru6jrCPNBXQ+V\n" +
                "mfZ+b8DluhTOmpBBRDN4v66HNXs62LC/m2KvlRzX4IOPo7rBX95v4vbXa+iJZdB0AhcH4NH+jBOX\n" +
                "AmsEPTtWzvmC1t4otV1hFpd5+i91P3mnpovvPlfFlsZe8t0Wbl9RyuxjbPPaZJEH32/kwtPKsFvN\n" +
                "tBST0cuad2vxBCNgQEN3hPU1XZxa4cXRz4rrsigwu9DFsjFeGg9UlHhmexub63twWyWKvP2vld/Y\n" +
                "HeGpbW3c8lo1b1R1HozdfBI4JwBr+zlcfAoMoBE2ZcfCKabsbg2xvzPC/GJ3QipR9ER0fv1OHb9a\n" +
                "W0dPRGfZGC+3nV5K3nE0vyDAE9vaWHpSAf4U1CszMUkXnn2jmixd57yJfp7a1kooarC9OcjpFb4B\n" +
                "rU48Noll5V4WlHroCumsq+nixZ3tPLW1lf0dEQwDHIp0VDGHlt4o25p7eae6m8c+auHX79TzwIYG\n" +
                "1td00R3WAd4hVjpndWOsNHy/6a+p8nliTS7PeWFHGw1dEW5YUkiOc+jy2V7e1c5v3qmnsTuC3y5z\n" +
                "7Un5LBkTn7WX7VKob+qlojjx1qGJSbpS19DNtFw747LsnDvRz6OBFjbWdvPnDxr57LSjqrHHTYXf\n" +
                "xveXFdEWjPLCjnZe29POE1taDsVuyaJwqJ9sMKofVZXiAD3EKq8+EIAXByzMAfplUzaClg3/JBaD\n" +
                "Or++KyI9va0VQRAY67cNOHXBAN7e28lPXq3m0Y9aCGsG56uZ/HB5MeOy4i8t/F5dN06/nckV8Ufw\n" +
                "m5iMNO5/eAuryn1kOxWm5jp5cVc73WGdTfU9zC1ykTXI3hE2WWRSjoMzxmWwrz3M7tYQwOO6QW1Y\n" +
                "M6JhzdB0gyix9oh7gbeIleG6lVjJ6L81DlFT6347iw7UBfuhCn8FftkT0Vfet76eB99rZE6Rk2l5\n" +
                "TqblOxmbYTuhubqvPcRruzt4ZnsbtZ1hZFFg1bgMPjMtiwJ3/4Po8p0KNfVH51uamIwWdMOgvjVI\n" +
                "njumpOyKyPWLCvn2s1VEdYNbXqvmt+eOPWQpDZbDgl1fCMCvhmTQfjBgb3cg1q9x1YEaYTeENP3M\n" +
                "N6o6pTeqOgGwSAJFXivFnljvuYMOxJ6IRn1XhG1NvdQdSP3JdSl8bkYOZ43PGFT7pwKXwpu1ffa/\n" +
                "NDEZFTS2BBGIFRE8yPR8J5dMy+KhjY1Ut4e56+1arltUOCTzdf63hFVKEmEHvV13YOfgXBVygE8B\n" +
                "K4BFYc3w7GoJsqvl2Enl5Rk2vruooF/LxOOR7VTYvTW1wbYmJqlkX103xX77UbuDX5iRw7amXtZV\n" +
                "d/Hc9jYmZts5Z8LR4Uj95f3aQ9k5GwY92AAYsniDQCzd6G7gbjUWllUEVADeA/+CxHq+VQOTgb/O\n" +
                "KXINmfICeGlXB3VNvUSiOsoQmcgmJsOJXdUdFHuPNoYEAb63tIirn9jNvvYQ//t2Hfluy1EFFvrD\n" +
                "xtpudrUGAXYCrw14oEGQkICpQMwvv49jtEhS4SxgSDPe36jq5LntrVgtErtrOhlXmrhGvCYm6cqe\n" +
                "mk5Kj+E/dlkk7lhZyjVP7qaxO8LNL+3jttNKjlnj73iEojq/fKv24MOfHPjOJ51UmSk1AFVtQ9Mg\n" +
                "orYzzM8P5H3ZFJEdezuGZFwTk+HG7n2dlPRhgR0k26nwP2eUke1UCEV1rn++ild2t/drjqhu8MOX\n" +
                "97E3lrr3JvDHQQk9CFKlwJ4Cguuqu/osZtgfusMaN76wl86QhigITMt1sHOfqcBMRifb9rYfald2\n" +
                "LArcFu5cVYZVFglrBj9+pZr/eav2YErPcWnuifKtZ/bwn+ougCbgklRZX5AiBRaAFuChsGbwwIb6\n" +
                "AY8T0nS+/9I+qtpCCMA35+ezoNhFYHvyyoCYmKQLNQ3dRCI6Jb7jbwiuq+7ixuerCEX1/cRitHhi\n" +
                "Swuf+ec2/vJ+E629R1ef6A5r/PWDJr7wyA421fdAzOe9PBCL80oZqUwa/B7wqSe3tLqn5TmPqi5x\n" +
                "InoiOje+UMUHdbFdkC/NzuXM8RlUd4T55do6dN1ANHsMmowituxqZ0yW/ZjpfZvqe3hoYyPv1HRB\n" +
                "LLd5JRAAvg38sD2o2e5/t57fvVvPGL+NMp8VRRKo74oQaOg5WO4GYhH0Xwwcw8edTFKmwAJQr8JV\n" +
                "Bvzpp2tq0AyD08bGV3N/Z0uQ1S/vYMaqCgAAHQRJREFUo7ojjABcNS+PT0zKBGKBdRZRYFdNp5lS\n" +
                "ZDKq2LKnjT3NQX75Vi1T8xxYZZFQVGdnS5A3qzoP+qwANgIXBmK7hwC3q/AXYn0YLzMgo48QqAgx\n" +
                "xfWrADydrGs6ESkt2xCAh1SYFNWN6297rYb3a3u4ck7uMUtMtwc1/rqpiUc+bD6YZ6UrkiBqukFU\n" +
                "Nw7tak7IdbBpW4upwExGFe9sbtJ7w5r4+GH5iR+jHbgd+EUAjiied8Ca+oYK3wXmACqxvOdOYtVR\n" +
                "NwRixQbTirRYY6kxzX87INkVkeXlXuYVuch1WYhoOnvaQqyv6eatvR2Hm7E7gEuJmcE/KPFauWpe\n" +
                "HnOLXDz0fhP1Dgs/uGpmiq7IxCS5RKI6Sz7/VDSq6Z8A5gKzgVygF9gCvEysVPOIyrVLCwUGoMaC\n" +
                "W38GrDrBoe3AXcDtgVhmOyrcQCxRlAq/jZmFTl7e28XTv16ZSJFNTNKGzdtbufqWN1veDWmZqZYl\n" +
                "maRN5b8AbAbOUGPR++cRay1eAWQQM2M/BJ4FHglAx8fOvU2FdcD9O1qCZTtagsiSGEuryOt/kJ6J\n" +
                "yXDjvS1NyLL4FqHBhSUNN/5/e3ce31SZ7gH8d7YkJ0sppdhAgUJlPSk03ZBFZFFQQMANRx0FrIMO\n" +
                "V+GKy8yooKNeHK9XB1HHcRTjgoqyuKDigMMqIKsgkEChLKXQptRC2zTJ2c/9I0EUWbpB2vT9fj79\n" +
                "fOghfXianDx5z+F9n7fJFLBTfJFLwxfr8XMrhEjDxUkA7jCb6Ku27C6nSAEjWoL124+LgaCyJNZ5\n" +
                "XGpxtWDQB8g+4E0fMCQsajM27Cgj2xQRcU+SNezcd4IDsCzWuVxqcVXAfkk3jEUbdx6n5FrMLiaI\n" +
                "5mzH3gqYTXRZrCeVxkKTu4RsLD5gXy5Ll2/z/dSuf+ZlsU6HaCYkWcOhYzUoKgkgGFahGwYcVg6p\n" +
                "KTZ0T2sFE1f7z3xR0rCvqAolx0MIhBSwDAW7lUNaezu6pDrq1TGlsLgaG7aXwXegUj54LCAHwwod\n" +
                "FjXOMCJbH7Y0cVvAAMDQjYWrNpc8kNWzDW05x9yy2lI1HeHoeksb37CnrawijL2HKnGsLHJiUxRg\n" +
                "41l0SLEjo2trtEms/aYkR8uCKDhUhaNlQYiyBpoCWjlM6Oi0o1d6IhLr2NlWVnRs8ZZjw/Yyfdf+\n" +
                "E+Fjx0OMKGkcRUG3Wli1o9NuuHu24QdmXUa5e7ap9aYux0+E8d02PzbtKpf3FVUpJ6skTlZ1lmVo\n" +
                "NamVWemelsDmutqah/Zth8uSzt9iSVZ0rN9ehs27y/UfCyrCpeUhVpJ1lqah23hO7dTOZuQIydYB\n" +
                "7hT07pZ0wc7Aiqpj5aYSfL6qSNyxt4Izc0wVx9KFAE5SFHTDQLKi6umSoiW6e7ZRxgzuZBneP/Ws\n" +
                "BUiUNPx7/VEsWV0k+gorzRYLU27mmIOablRQAEdRaCPJWjdVM6x5GW21G4almQdlO8Gcpx27phlY\n" +
                "+l0x3v1in1hWETbMHPOfQEhZAWAPIpthWAAUXeg1iEdNZhrFxSAAVyHap8hiZuQuqQ61f+Zl1qF9\n" +
                "26NH5/MvXSo/KWLFxhKs3+4XCw5XGZUB+ed3FU1TertkazhHaGMelONkB7hTLvhpWhmQ8cXKIny+\n" +
                "sijkrwiZbDxXCGCPquplAMDQVFuKplw1IaV7Wnu7OG5ImnXs0E6wn6V/efkJEYu+PWR8teaIeLJa\n" +
                "Zqw8u8cwDJ+mGVUAKI6lU3XDcAXDalrXjgnSTcM789cN7ACr5dyFNxhW8eHXhfj4m4MygHJF1RdI\n" +
                "srYekc67lYjcbkgCIFh5drihG2PtNs46aVx3yw3D0s75++89VIl/frJH2ry7nLHx3MaaoLJYN4wd\n" +
                "iEycDAHgAXSkKSrbZmXHhUR1YJ6rrX7frT1Nrst/vbdBdY2M978sNBYuO6gyDF0iK9oCWdHXITLR\n" +
                "sgoAdypHG89ep+nG2ESHyXTvLT0tI6/s+JsiYRjAN+uK8cqHXklW9JJQWJ2tG8YX57oUE4BUmqJu\n" +
                "4C3MQyaOSZ32e5d51KCOoChA1w0s/s9hvP6xT6Ypan8gpMwGsMQXWbJztljpDEPdbDYx0xNsXOuH\n" +
                "JvS2DMlr95vH+Q5U4sl/bBMrKkV/MKzOALDQB8hnfbJboLguYKcIkU+oTgCybTw7VlH1cWntHfT9\n" +
                "t/WyDHCn/OqxR0pr8MaCvcqqLSWU1cJ+HwgqHwHYBOCQD6gUIqPWywAIJo4eZuKYOwGkTBzbzfS7\n" +
                "69Jx5khPVnTM+3K/8c7n+zQzx6wPhJSXAPzHF5lgeLZcrQBGOazco4qmZ/1xfC/utpHpoGkKIVHF\n" +
                "W4sK9AXLDuoWE7MsEFJeBbDCB5x173chMgVljMPKPQIK3af93mUeOyTtNyOSVZtL8T//2i4D2BwI\n" +
                "KY/7gO9q8ZzSAK638eyLCXZTp1lTc80Z3U4XnJCoYvb7u9Wl3xXrFIUXZUWfE216eaG4yRYz86Cm\n" +
                "GY+MGJDKPDqpD2vlWXyzrhj/+/ZOmaaptTUh5TEfsLUWsVgA42w8+1Lb1hbnMw/kmnt2iXxwVdXI\n" +
                "mPHqNnlnQUVlWNKmIDLJs9ZdFQRgHG9m3szolpT44J0u06y3dkiHS2r8obB6jw9YUYc4NE1Td5o4\n" +
                "+pWBWSnWmfdmcdboCH/BsoOY84FXoSg8Liv6y+d6nYkWRgD4DIq6P8vEVE92p0jF/7rOqP5wrPHy\n" +
                "LT31TJZWci3sGwJQ66bhAjC0r5X7YXCSRVz75ECjZv44o2b+OGPP7GuMUakOMZdndwlATj3yvDKP\n" +
                "Zwtv6tJKXPanfsaQJF7M49kNAtCzHrFG5lrY0gmuZOnYWyONmvnjjMBH44ynr0tX3RxdI0Tm3tWZ\n" +
                "AFBujn4wk6Xl9/L7GDXzxxkFc64xrrnMKubx7Nq6PI9nxG3X18qtujrZKk4f0EHNNjMnhMiqi/rE\n" +
                "Yt0c/YSbo6VPpmQbe2ZfY1ydbBVzefZzAXDUJ2Y0rj2XZz8VACPHzMwVgHpvSCoAbXJ5dtXoDg7x\n" +
                "yBvXGbNv7qG7ObpKAMhyEuLsBCAhl2cXD0wwi7f3SJJyefaAALjqGYvKoKnJmSwtzZ3Q29j47FVG\n" +
                "Ls9KWSbm/4Q6bl93RlxzLs/OdVHQ3Rz9iNCAUfOpN9zVyVax8NURxvQBHeRcnj0kREanDSIAOdlm\n" +
                "pvJ/Rl+u97ObpBwz81JDco3GpLJMzPM5ZsYrAM5GyHFQJkuH+tlNcq6Ffbmh+f0i7pWNFIfJ5dl5\n" +
                "AxPMipujq6OTugni/DJZ+k+5PPtN9PKtQQQgy83R1ZksrfZh6PsbI79o3MsbKQ6Va2FfzzIxWq6F\n" +
                "LRaA+u90+tvYrkyWrsoyMX9rrJjRuI22440AuHsz1N8bK15jEwAmk6U/FiIrUQji0hOAXgIwJdZ5\n" +
                "nIsAUBkU9bIAdLkIsRs9JkEQBEEQBEEQBEEQBEEQBEEQBEEQBEEQBNEiCUDr+qy2aInith8YcX4C\n" +
                "kCYAWRch7kAhsqi6yRKAvo01C7+xCQBnc/BfmS2mdQLQIdb5NHWkgF0kAjCuIUuILiYBaGWz8ytN\n" +
                "Zm51Yy5XEYBhDMustidYFzTW7y4AlAA02kYVGQw9jqapDTYH/47QCOd/NL8LbURT21gmm53/NLVj\n" +
                "Sk6fnO6tTSbulcaISxB1kmUxTRMAo2+C9bPGGI0IAC8AdzfGqEEAWufZ+R+njMiTZ0+/Rc/mzVVC\n" +
                "ZAuuBslg6JvdJk6a99xkY2R6ezHPwS9t6NIsAbDk2fkvsnlzuQD0aWiOfThmUibHyp/8/b+MUV1T\n" +
                "xb4J1lUNKY4CkJTn4L8VACPPzi8QAHsDYqXmOfit491dpc1LZhmZHCvXd10uQdSLAFDZvHlWnp2X\n" +
                "137ypHFzn3Qxz8Fvb8ilgAB0y7PzezI5Vsuz8ysa+IZz51jNR6eO7ifXFH1sSCWLjA/+NtnI5FjZ\n" +
                "beYeF+rR4FIA7Hl2fm6uzSKv+miGIZUsMir2vm9MHJgh5dosR4TIHoX1yTUvz84fuvOKXtI/H7vD\n" +
                "yORYqQ/HPFjPHBPzHPz8PAcvf//Zs4ZUssg4uf8DY9r1/eVs3lyVwdD5dYkrAHQGQ0/I5s0np466\n" +
                "Qi7c8Jox6creUq7NUiYAN9Xlg0YAuD4cM81t4oIz7xyuVh340Li5T7qYZTE16npSooUQgMFCZEfi\n" +
                "uv5c1zwHv2lEWopY8N0rhlSyyAgcnm88OWGEksmx4d4s87AQad5X23j2bN78jNvESc9PGasd971r\n" +
                "/PeY/nKWxVSdwdCT67LAWQDa5Nosr2ZyrPzmjLsM8dhCQypZ9PPXjmUvGKO7dQjn2izHMhj6ntqM\n" +
                "nAQguTfL/Nlt5qrvHtRHKtryxq9iho8uNOY+NdFwmzklz8F/KQD9aplr3zwHv8Rt5uQ3Z9ylh4sX\n" +
                "GFLJImPzklnGdV3ahfMc/GEBuEuI9Hi7UKzLMjn2KbeJC/5xeI5UsmPur3KUShYZy9/9szEiLSWc\n" +
                "a7OUZXLszPONegSgVybHPpFrs5QOS00Wl8599Oc44rGFxuI5U41BbRPFPAd/OIOmHjnXAnwBoAUg\n" +
                "K5NjZ+VYzSdHd+sQXrfwr4ZUssh4a+YEI1r4692apyVpkjcyY0kAMmiaWs5y7DuypLwR3XL9fI/P\n" +
                "stn56bKk3DZ+4rX01MfuYCz8r8+97Zv34v+e9IgHCoo1lmXmhYLiQgAbT23M+4tYdgD9eKv5Dk3V\n" +
                "b3e5L8ejz+RbevVJ//kx61b8gBefelc87q8QAbwTDkmLAWw5s0unEGlmeJXNwd8uifJN/a7KNB55\n" +
                "epIpLb39WX8PXTew7It1eOe1z8OHCo8yNrt1faCqZoWuGwUATiByT6sNwzKC3WEdXRMIZeb0E9T8\n" +
                "qTearxh07qu7ivJKvP/GEmPxvG8VwzAqKYpaGqwJbwJwFJGt6nkAHW12vh8oXKdreptb7hrBTvjj\n" +
                "WDo55dcdWTVVw9JPv4Pntc/EY0VlsNosawPVwRW6bhwAUIFIUU/mODaDt5pH1dSEMwYMcWt3P3Cj\n" +
                "KfuKXufMUdcNfLdiG5Z8vEresHoHNFXTrDZLESjqBEXBMHQjORySOlI0xfUfnImxvxvKDR6RB5r+\n" +
                "7dtHVTSsWLoRSz5ZJW7d4GUpmhItvLmYovCTYcAEw2gdDIY7m0wchlybR427bRiXNzADFEXhQEEx\n" +
                "7rj2T4osKwNq07CRIAXsrNwmbposK3MoioLdYS3UdX1NsCa8E8BJRN7IbR0Jtr66rg/WND1x7O+G\n" +
                "0HfdO4bp0Pn8Lat2/bAfXy5cra1dvlU+7j/B81bzSZZlqgBAUdRWYlhu7UxNDl096grL6FsG0z0z\n" +
                "zt7YwTAMbFm/G18tWqNuWLVDPfFTlclqs1QwDB00DNCqqiaKYdmRlt4ufPXoftYxtw7BuQrX2RQf\n" +
                "9mP9qu3w/XhAO3ygRAxU1oCiaSS2tqNrrzReyLycHjjUjbYpSbWOqSgqtm/ag63fe7F31yGxrKRC\n" +
                "FcMSzBYOKe2T2R6uzpbsfgL6DuwNlrvw/f+D+45i49of4d1RqB45VCpVVwYNhmXQOslBdRc68y53\n" +
                "V3rAUDeSks/fOvxMuqajuMiPIwdLEagOwdB1JCTa0SEtBWnp7UEztb/vr6kaDu4/htKj5aiurAHL\n" +
                "MbA7rOjSrQPad2wL6hetcRVFxe0jHpWOHTn+0g9h6Yk6Jd2CkQJ2DnkOfnnvrO5Dx08cwe7ddQiH\n" +
                "Co9J1ZU1GsuxSEpOMKV368D2zu4Od17PWr3hzhQMhFFc5EdNdWQQZk+wokOnFNgT6n7fu+pkAMWH\n" +
                "yyCGJdA0hYREOzp2dsJsabQ2WsRF9sJMj7bkk1W+YE04m7SOrj1SwM5BAJJ4q7lg6mO/T779nlGx\n" +
                "ToeIY2u/3YZHJ78YkmXF5QMOxzqf5qRJzlNqCsqBcGtFW71l/e6JeQMzGGdqcqxTIuJQSfFx3Hfr\n" +
                "07KsqHd5DWNDrPNpbshE1vPwAVt0Q5/64MTn5bLSilinQ8QZSZQx9a7nJF03/rVb0xfFOp/miBSw\n" +
                "C9ipaG/KsvLB1DtnSWJYinU6RBx5avo/1LLSip1iWJoe61yaK3IJWQutFW2pJMkjC3YfThk+ZgBD\n" +
                "1XI3aoI4F8+rnxqffbSiIhQUB/qAYKzzaa5IAauFckBPlNXF/pKffn+yojph4NAsUsGIelvx9Ua8\n" +
                "MMMjSaI80AcUxTqf5owUsFoqB8TWivbp/j1H7rbwZr5PTvdYp0Q0Qzu27MX0u/9XlmXlBh/wfazz\n" +
                "ae5IAauDcqCqtar9e9sG76TL2rVhzzXRlCDO5uC+o/jDzU/KqqpN2a0bC2OdTzwgBayOyoGyJE1f\n" +
                "sX7l9gmdu6Yyl/foGOuUiGag+LAf+TfMkBRZ/dsOSXk51vnEC1LA6qEcONpG1zes+Xbrbd2FNLbz\n" +
                "5amxTolowspKK3DPjU9KoWD4n9tC0mOxzieekAJWT+XAoSRN37Dym82kiBHnVFZagUljn5ArTwbe\n" +
                "3xaSHoh1PvGGFLAG+LmILd18e2qnFKZbr7RYp0Q0IWWlFZg05gkpUB2cHw6Jk8tjnVAcIgWsgaJF\n" +
                "7Lt1//nhtlaJDtblbrQOzUQzFrnnNVMKVAffDgXF+3yAEeuc4hEpYI2gHChK0vRlm9ftukORVS53\n" +
                "gIsik11brgLvYUwa+4QcDotztoWk6WTkdfGQAtZIyoGSJE1ftGfngfGHD5Twg67JoZk69I4i4sOW\n" +
                "9btx361PK6qiPrZdlJ+NdT7xjgwTGpkAtLXZ+ZVde3bqPue9v5hatXbEOiXiElnyySrM+vObsqpp\n" +
                "E3dr+sexzqclIAXsIhAAi83Of2R3WEe9Pn+mOb072d4vnumajtee/8j4cO7XQVlSrvMB62OdU0tB\n" +
                "LiEvgnJATZTVBZqm04s/+PbKTl3akQmvcSpQHcT0u1+QV/1781ExLPX3ATtjnVNLQgrYRVIOoFTV\n" +
                "1iSp2qa1y7fedOKnauaKQb3JfbE4sn9PEfJvnCkVF/lXBmvCw3xAWaxzamnIJeQlIABpNjv/ZfuO\n" +
                "bbu/9PafzB0vsPkH0fR99tEKPP/4XAUUnpYl5TkyTSI2yAjsEigHqhJldW44JCYueHdZbltnEkMW\n" +
                "gjdP1VVBzJj2qjL/7aVVkiSP3KXpH5JpErFDRmCXmAAM463mBTn9XY6nZ99vquu2X0TsbF63C3+Z\n" +
                "MluSZWVlMBC+yxfZi5KIIVLAYkAAWtkc/JuGbtzw2HOTTdePHxzrlIjzCAbCeHnWPO2L+SsVTdf/\n" +
                "a7emvxPrnIgIUsBiSABG8lbzvN7Z3R0zX7jPdKGNcYlLb92KH/DXh16XJEneWFMdmuADjsQ6J+I0\n" +
                "UsBiTADsNgf/giwqf5h0/w3sPdNuosiGtLHnL/kJzz/+tvL9mh2iqmrTdmv6u7HOifgtUsCaCAHI\n" +
                "tCdY37Xw5l4PPzXRfO24gSDrKS89SZTx/j+XGHPnLFY5E/tRsCb8kA84Eeu8iLMj75AmRIi8Hrda\n" +
                "bZZX09LbJzz01ERz7gBXrNNqEXTdwL8/X4e/P/2eJEnynprq0GQfsDXWeRHnRwpYEyQAFo5j/5ui\n" +
                "qZnuvJ7sA3+5w9w7u1us04pba5Zvwexn5onlZScqQ0Fxqg8gm8w2E6SANWEC0Mpk5h6FgYczsrvR\n" +
                "906/xXTFoD6xTisuGIaB1cu24I0XF4jFh0vDkig/puvG2z5AjXVuRO2RAtYMCECiycw9AgPT0y5v\n" +
                "z9z30Hjz0JFXgKbJy1dXiqJi+ZINmDtnsVhW8lNQEuWZum684wPEWOdG1B15BzQjAmBnOeYPJhP3\n" +
                "eEKi3XHHPaMsY24disQk0rLnQk5WVOPz+Ssw719fSrKslIZD0rO6pn/gA+RY50bUHylgzZAAsACu\n" +
                "T2hlezgUEvsPG3mFPn7CtVxOf4H8z+UvGIaB7Zv2YMF7y5QVSzfSPG9ZH6gOPgdgOVm7GB/I2d7M\n" +
                "CUCa2WKaQlHUfYlJDvNNd1zDDx/TH527ttxdko4e9uObz9Zh0bzlYnVVUDIMY64kyv/wAYdinRvR\n" +
                "uEgBixPRUdlom4O/RxLla9ulttXGjB/SYorZsSPHsXLpRny9eG24sKCYs9ks31ZXBV8DsMwHaLHO\n" +
                "j7g4SAGLQwLAAxiV0Mp2dygkDk9p10YdPCKP7z84k8od4IKFN8c6xQZTFQ07txVgw+odWLZkQ7j0\n" +
                "aDlntVrWBqqDcwF86QNqYp0jcfGRAhbnosVsqNVmuZ5m6NGhoNghw91Vump4Lp/Vtyd69k6H1WaJ\n" +
                "dZoXpKkafD8ewPbNe/H9mh/FHzb5WJZlqiia+iYYCC8E8K0PCMc6T+LSIgWshRGA9gCG90hNnBqU\n" +
                "1JySE0F0SktRM3J7IDOnB9uzdzq6dE2FzcHHLEdZVnBo/zEU7inC7u2Fxo9bC8IHCo5YW1lNcKVY\n" +
                "1dUFP00FsNIH7ItZkkSTQApYC+XPd00C8E5A0rD6UPVnr2wu2253WIcpstInFBSTElrZxI5d2mld\n" +
                "e3S0dOnWgWnXoS2SU1ojKbkV2rRtBUeCrd7/tqpoqDxRjbLSChz3n0BZaQWKD/mNg/uOiocLjxnH\n" +
                "/RW82WIKmMymveGg+J0sKxs/urW7x2nnHACqnB5vYmM9D0TzxsY6ASK2HGYGY3q2PjJ5Q8mzOBl4\n" +
                "FohcdlZXBbt7dxR23bPzQC9Hgs1N0VQXTdVTJEluJUuKnWEZ3e6wSgmtbDpFUYbVZgHLMbDaeJrl\n" +
                "Tp9W1ZUBQ1U0hIKiEawJ0zWBECeJMkcztMbz5pMsy5QBKAoFxV2KohYAKATg2xaSKhCSfo7jtHNz\n" +
                "L/FTQzQDpIARvxG9l/QjgB+hG0Dlr++HCwCrqVrbqpOBtlUnA0nRwwkAaAB2/Pq8qgagAAhEvyoB\n" +
                "HN+t6QHUkFtWRMOQAkbUWXS9YGn0iyBihuzxRRBEs0UKGEEQzRYpYARBNFukgBEE0WyRAtZyNbc5\n" +
                "gORcJX6DnBQtkD/flQDggV8cut6f72oXq3wuxJ/vygdwqumZw5/vGhPLfIimo7l9ChMN5M939QTw\n" +
                "OYAeZ/xVFYCHAXicHm+T6JXlz3eZALwMYMpZ/vpjANOcHm/5pc2KaEpIAWtB/PmuGwG8h9OjmVMM\n" +
                "nD4XVgKY7PR4D17K3M4UHREuAjAgeuiXOZ5SAeBBp8f7waXMjWg6SAFrAfz5LhrA0wBmRA8ZiIxg\n" +
                "bo9+/zWANAAZ0e/D0cfOcXq8l7yXlj/f1R/ApwBObVW+EYALpwvvawDux+nz9xsAf3R6vGTX7BaG\n" +
                "3AOLc/58V2sAX+F08aoCMBbAgl887CcA2QCeQqRHPA/gJQDf+/NdGbiE/PmuKQDW4HTxegvAYAD6\n" +
                "qcc4Pd6pAK4CUBA9NBLAbn++6/5osSZaCPJix7Fo8dmCyBscALwA8pwe71dnPtbp8SpOj/cZAFmI\n" +
                "jHgAIA/AD/5811/9+a6L2gXRn+8y+/NdbwN4HQCHSCGd7PR473V6vL/ZeMPp8a4D4AbwHCJLmxyI\n" +
                "jMzWRO/zES0AKWBxyp/v+h0ihejy6KHFAPo5Pd795/s5p8frAzAQwIMAgogUk6cAbPPnu/pdpFw7\n" +
                "AlgHID96qATAVU6P97wdKJwer+j0eJ9ApNBuix6+EsAOf77rcX++i6z1jXOkgMUZf76L8ee7XkDk\n" +
                "HpcNkUuvvwAY7/R4a9Vm2enx6k6Pdw4i98S+jR52AVjvz3fd3Mj5DgawFUBu9NBaADlOj3dTbWM4\n" +
                "Pd4dAPoB+DMi+zuaAcwC8GVj5ko0PeQTKv60AtA5+ueTAG5zerzL6xPI6fEeBjAi2vzwBQCvIHJz\n" +
                "vTGlAzjV0/o1AA85PV6lrkGcHq8K4AV/vuszRO6bcTg9oiPiFPlfyDjlz3ddD8Dr9HjPupWYP991\n" +
                "A4DPot++5/R4J10gntnp8Urne0x9+fNdqQBynR7vF+d5TCUixRlOj/e8560/30UBYOtTCInmhYzA\n" +
                "4tTZbtQ3MN5FKV7R2McAHGvEeAYiTRSJOEfugREE0WyRAkYQRLNFChhBEM3W/wOpKRptvrszbwAA\n" +
                "AABJRU5ErkJggg==";

        String imageFilePath = "d:\\图片\\base64转化.jpg";
        boolean b = base64ToImage(imageBase64, imageFilePath);
        System.out.println(b);
    }

    /**
     * 图片转化成base64字符串
     * @param imageFile
     * @return
     */
    public static String imageToBase64(File imageFile) {//将图片文件转化为字节数组字符串，并对其进行Base64编码处理
        InputStream in = null;
        byte[] data = null;
        //读取图片字节数组
        try {
            in = new FileInputStream(imageFile);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data);//返回Base64编码过的字节数组字符串
    }


    /**
     * base64字符串转化成图片
     * @param imgBase64Str  图片base64编码字符串
     * @param imgFilePath   图片保存路径
     * @return
     */
    public static boolean base64ToImage(String imgBase64Str,String imgFilePath) {   //对字节数组字符串进行Base64解码并生成图片
        if (imgBase64Str == null) //图像数据为空
            return false;
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            //Base64解码
            byte[] b = decoder.decodeBuffer(imgBase64Str);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {//调整异常数据
                    b[i] += 256;
                }
            }
            OutputStream out = new FileOutputStream(imgFilePath);
            out.write(b);
            out.flush();
            out.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
