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

    @PostMapping("/encrypt")
    public CryptoModel encrypt(@RequestBody CryptoModel request) throws Exception {
        log.info("-----Encrypt Api: blankText-----: \n" + request.getData());
        CryptoModel resp = cryptoService.pgpEncrypt(request.getData());
        log.info("------Encrypt Api: encryptedText------: \n" +  resp.getData());
        return resp;
    }

    @PostMapping("/decrypt")
    public CryptoModel decrypt(@RequestBody CryptoModel request) throws Exception {
        log.info("-----Decrypt Api: encryptedText-----: \n" + request.getData());
        CryptoModel resp = cryptoService.pgpDecrypt(request.getData());
        log.info("------Decrypt Api: decryptedText------: \n" + resp.getData());
        return resp;
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
