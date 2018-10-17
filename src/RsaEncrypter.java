import java.lang.reflect.Array;
import java.math.BigInteger;
import java.util.Arrays;

public class RsaEncrypter {

    private BigInteger [] _privateKeyPair = new BigInteger[2];
    private BigInteger [] _publicKeyPair = new BigInteger[2];

    public RsaEncrypter() {
        BigInteger      p = BigInteger.valueOf(7951),
                q = BigInteger.valueOf(8707),
                e = BigInteger.valueOf(4099),
                n = p.multiply(q);

        BigInteger funcEyeler = p.subtract(BigInteger.valueOf(1))
                .multiply(q.subtract(BigInteger.valueOf(1)));

        definePairs(funcEyeler, e, n);
    }

    private BigInteger euqlidAlgorithm(BigInteger funcEyeler, BigInteger e, BigInteger x, BigInteger y) {
        //TODO
        return BigInteger.ZERO;
    }


    private void definePairs(BigInteger funcEyeler, BigInteger e, BigInteger n) {
        BigInteger x = BigInteger.ZERO;
        BigInteger y = BigInteger.ZERO;


        _publicKeyPair[0] = e;
        _publicKeyPair[1] = n;

        _privateKeyPair[0] = funcEyeler.add(BigInteger.valueOf(574099));
        _privateKeyPair[1] = n;

    }

    public String encrypt(String text) {
        StringBuffer buffer = new StringBuffer();
        BigInteger encrypted;
        for(char c : text.toCharArray()) {
            encrypted = BigInteger.valueOf(c);
            encrypted = encrypted.modPow(_publicKeyPair[0], _publicKeyPair[1]);
            buffer.append(encrypted.toString() + " ");
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
