import java.lang.reflect.Array;
import java.math.BigInteger;
import java.util.Arrays;

public class RsaEncrypter {

    private BigInteger [] _privateKeyPair = new BigInteger[2];
    private BigInteger [] _publicKeyPair = new BigInteger[2];

    static private RsaEncrypter _instance = null;

    private BigInteger
            _x = BigInteger.ZERO,
            _y = BigInteger.ZERO;       // holders for euclidAlgorithm

    private RsaEncrypter() {
        BigInteger      p = BigInteger.valueOf(7951),
                        q = BigInteger.valueOf(8707),
                        e = BigInteger.valueOf(4099),
                        n = p.multiply(q);

        BigInteger funcEyeler = p
                .subtract(BigInteger.valueOf(1))
                .multiply(q.subtract(BigInteger.valueOf(1)));

        definePairs(funcEyeler, e, n);
    }

    private BigInteger euclidAlgorithm(BigInteger funcEyeler, BigInteger e) {

        if(funcEyeler == BigInteger.ZERO) {
            _x = BigInteger.ZERO;
            _y = BigInteger.ONE;
            return e;
        }
        BigInteger d = euclidAlgorithm(e.mod(funcEyeler), funcEyeler);
        BigInteger tx = _x;
        _x = _y.subtract(e.divide(funcEyeler).multiply(_x));
        _y = tx;
        return d;
    }


    private void definePairs(BigInteger funcEyeler, BigInteger e, BigInteger n) {
        _publicKeyPair[0] = e;
        _publicKeyPair[1] = n;

        euclidAlgorithm(funcEyeler, e);
        _privateKeyPair[0] = funcEyeler.add(_y);
        _privateKeyPair[1] = n;

    }

    static public RsaEncrypter getInstance() {
        if(_instance == null) {
            _instance = new RsaEncrypter();
        }
        return _instance;
    }

    public String encrypt(String text) {
        StringBuffer buffer = new StringBuffer();
        BigInteger encrypted;
        for(char c : text.toCharArray()) {
            encrypted = BigInteger.valueOf(c);
            encrypted = encrypted.modPow(_publicKeyPair[0], _publicKeyPair[1]);
            buffer.append(encrypted.toString().concat(" "));
        }
        return buffer.toString();
    }

    public String decrypt(String text) {
        String[] strings = text.split(" ");
        StringBuffer buffer = new StringBuffer();
        BigInteger encrypted;
        for(String str : strings) {
            encrypted = new BigInteger(str);
            encrypted = encrypted.modPow(_privateKeyPair[0], _privateKeyPair[1]);
            buffer.append((char)encrypted.intValue());
        }
        return buffer.toString();
    }
}
