package com.sofdigitalhackathon.libertypolls.hepler;

import java.security.KeyPair;
import java.security.KeyPairGenerator;

/*
    Class helps to generate, load, public/private keys
    Sign the message and verify it;
 */
public class SignatureHelper {
    private KeyPairGenerator keyPairGen;
    private KeyPair keyPair;
    private byte[] out;
    private byte[] input;


    public static KeyPair Generate(){
        KeyPairGenerator keyPairGen;
        KeyPair keyPair = null;
        try {
            keyPairGen = KeyPairGenerator.getInstance("DSA");
            keyPairGen.initialize(2048);                // size
            keyPair = keyPairGen.generateKeyPair();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return keyPair;
    }
    /*
        Generate


     */

    /*
        Sign

          try {
                    Signature sign = Signature.getInstance("SHA256with d");
                    sign.initSign(keyPair.getPrivate());
                    sign.update(input);
                    out = sign.sign();
                    tvOutput.setText(new String(out,"UTF-8"));
                } catch (SignatureException | UnsupportedEncodingException | InvalidKeyException | NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
     */


    /*
        Verify

        try {
                    Signature sign = Signature.getInstance("SHA256withDSA");
                    sign.initVerify(keyPair.getPublic());
                    sign.update(tvInput.getText().toString().getBytes());
                    boolean isOK =  sign.verify(out);
                    Toast.makeText(getBaseContext(), isOK? "Veryfied":"NOT",Toast.LENGTH_LONG).show();;
                } catch (InvalidKeyException | NoSuchAlgorithmException | SignatureException e) {
                    e.printStackTrace();
                    Toast.makeText(getBaseContext(), e.getMessage(),Toast.LENGTH_LONG).show();;

                }
     */
}
