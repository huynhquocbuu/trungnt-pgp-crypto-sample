package hds.crypto.pgp.service;

import hds.crypto.pgp.model.CryptoModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;

@Slf4j
@Service
public class CryptoService {

    public CryptoModel pgpEncrypt(String blankText) throws Exception {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PGPHelper.getInstance().encryptAndSign(blankText.getBytes(), out);
        String encryptText = out.toString();
        //log.info("encryptText: "+ encryptText);

        //ByteArrayOutputStream desStream = new ByteArrayOutputStream();
        //PGPHelper.getInstance().decryptAndVerifySignature(encryptText.getBytes(), desStream);
        //String decryptText = desStream.toString();
        //log.info("decryptText: "+ decryptText);

        return new CryptoModel("pgp-encrypt",  encryptText);
    }

    public CryptoModel pgpDecrypt(String encryptedText) throws Exception {
        String txtTest = "-----BEGIN PGP MESSAGE-----\n" +
        "Version: BCPG v1.46\n" +
        "\n" +
        "hQEMA1Vd3fJAfWorAQf/b07qY1Vi9/sHLsGPgGgK/zZ5uoL/IrIptpNvpR4WNrRT\n" +
        "QoyX+y81kH+sKRTbSgsKJjjYC4a5Pv960I/nvUWICvttxD6GMyh94ItIEKWA96rV\n" +
        "pkfKEXnWqEyUf7DSjsEm2pOTPo+ry9xfrCbFnqz+2HQDgU+iyQ8yOu54hWvbljBa\n" +
        "REBeOQZ0kfi4ViKOUXPueWH9NLind6VgyyimNsvL27MRx7gyGbLqgVgNypcH4OyO\n" +
        "UQzzuqsrqFUK8yGIWhpD6Vhjm9uTlhUJ3GkF5WSrqv9sajxjAh9KfZuChPUqypUs\n" +
        "h84KCWQr5ySzUzO3b2qL7X0cKje9S7QseA0J8u0jX8nAyWOc/eZ1OuSqBbJVGBS2\n" +
        "exutHDjwMHgGVbQFX+pY/XND2LY+BxLM3McOEpUwKyjDFL9ictDD6Irb1sCkziag\n" +
        "a82litphaViaxWBcTqDt/0vum8ItaWqFxL3Ahj8AwEepc/RkZ5A13m7OewRrmVKO\n" +
        "Ceh+u9w0/avpJFoVi2yd//cyaOtGZIUuRV1makRxN8pguI7irg9aqxNE247rWvPz\n" +
        "KVUlerKCVPLF/qANXDskU/pYQHyBAH/XZFpbxEGP8XQ1iHiViqzR4ihLjJ/aXPo2\n" +
        "HwVoyibrFXOsX25IyD/CimxVv10/UEPBTbTCMsvyQazbL2ESPsJ1t5VuzspS/F84\n" +
        "9x2Pq9Bup2WlZohMpNzjEpJ7NsCF2JCWT5cLZ09CH8JCZeHxYgLT2ES9znBT2G7Z\n" +
        "AfqaAc47FPaKs6DN594wWtPl5vxmov9TyyHoWASeYrS09AcuGwSSsmVSAs7ngMKk\n" +
        "EZTo32t8GLaMjvOtYZNLpPTmwmsuqllG3p4obT5MlfPKIhd9KyKL3o3pag==\n" +
        "=jFWJ\n" +
        "-----END PGP MESSAGE-----";


        ByteArrayOutputStream desStream = new ByteArrayOutputStream();
        PGPHelper.getInstance().decryptAndVerifySignature(encryptedText.getBytes(), desStream);
        String decryptText = desStream.toString();
        return new CryptoModel("pgp-decrypt",  decryptText);
    }


    public CryptoModel pgpSign(String textToSign){
        String txtSign = "";
        return new CryptoModel("pgp-sign",  txtSign);
    }

    public CryptoModel pgpVerify(String textToVerify){
        String verifyResult = "";
        return new CryptoModel("pgp-verify",  verifyResult);
    }
}
