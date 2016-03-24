/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controllers;

import java.util.Locale;
import javax.speech.Central;
import javax.speech.synthesis.Synthesizer;
import javax.speech.synthesis.SynthesizerModeDesc;

/**
 *
 * @author haikal
 */
public class VoicerService {

    public   void getVoicer(String voice) {
        try {
            System.setProperty("freetts.voices",
                    "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");

            Central.registerEngineCentral("com.sun.speech.freetts.jsapi.FreeTTSEngineCentral");
            Synthesizer synthesizer
                    = Central.createSynthesizer(new SynthesizerModeDesc(Locale.US));
            synthesizer.allocate();
            synthesizer.resume();
            synthesizer.speakPlainText(voice, null);
            synthesizer.waitEngineState(Synthesizer.QUEUE_EMPTY);
       
            
        } catch (Exception e) {
            System.out.println("Sounf");
        }

    }

}
