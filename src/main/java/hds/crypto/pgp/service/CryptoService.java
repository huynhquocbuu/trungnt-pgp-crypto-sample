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

        return new CryptoModel("pgp-encrypt",  encryptText);
    }

    public CryptoModel pgpDecrypt(String encryptedText) throws Exception {

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
