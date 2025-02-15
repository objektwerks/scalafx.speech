package objektwerks

import com.google.cloud.texttospeech.v1.{AudioConfig, AudioEncoding, SynthesisInput, TextToSpeechClient, VoiceSelectionParams}

final class Speech:
  val voiceSelectionParams = VoiceSelectionParams
    .newBuilder()
    .setLanguageCode("en-US")
    .build()
  val audioConfig = AudioConfig
    .newBuilder()
    .setAudioEncoding(AudioEncoding.LINEAR16)
    .build()

  def textToSpeech(text: String): Array[Byte] =
    val synthesisInput = SynthesisInput
      .newBuilder()
      .setText(text)
      .build()
    val textToSpeechClient = TextToSpeechClient.create()
    try
      textToSpeechClient.synthesizeSpeech(
        synthesisInput,
        voiceSelectionParams,
        audioConfig
      ).getAudioContent()
       .toByteArray()
    finally
      textToSpeechClient.close()