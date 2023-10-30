package hds.crypto.pgp;

import hds.crypto.pgp.model.CryptoModel;
import hds.crypto.pgp.service.CryptoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/momo/crypto/pgp")
@Slf4j
public class CryptoApi {
    private CryptoService cryptoService;

    public CryptoApi(CryptoService cryptoService){
        this.cryptoService = cryptoService;
    }

    @PostMapping(value = "/binding-text", consumes = "text/plain")
    public String bindingSample(@RequestBody String text) throws Exception {
        CryptoModel resp = cryptoService.pgpDecrypt(text);
        log.info("------Encrypt Api tttt: encryptedText------: \n" +  resp.getData());
        return text;
    }

    @PostMapping(value = "/encrypt", consumes = "text/plain", produces = "text/plain")
    //@Produces()
    public String encrypt(@RequestBody String text) throws Exception {
        log.info("-----Encrypt Api: blankText-----: \n" + text);
        CryptoModel resp = cryptoService.pgpEncrypt(text);
        log.info("------Encrypt Api: encryptedText------: \n" +  resp.getData());
        return resp.getData();
    }

    @PostMapping(value = "/decrypt", consumes = "text/plain", produces = "text/plain")
    public String decrypt(@RequestBody String text) throws Exception {
        log.info("-----Decrypt Api: encryptedText-----: \n" + text);
        CryptoModel resp = cryptoService.pgpDecrypt(text);
        log.info("------Decrypt Api: decryptedText------: \n" + resp.getData());
        return resp.getData();
    }

    @PostMapping("/sign")
    public CryptoModel sign(@RequestBody CryptoModel request) throws Exception {
        return cryptoService.pgpSign(request.getData());
    }

    @PostMapping("/verify")
    public CryptoModel verify(@RequestBody CryptoModel request) throws Exception {
        return cryptoService.pgpVerify(request.getData());
    }
}
