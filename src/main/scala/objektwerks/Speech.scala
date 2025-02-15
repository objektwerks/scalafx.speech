package objektwerks

import com.google.cloud.texttospeech.v1.{AudioConfig, AudioEncoding, SynthesisInput, TextToSpeechClient, VoiceSelectionParams}

final class Speech:
  val voiceSelectionParams = VoiceSelectionParams.newBuilder().setLanguageCode("en-US").build()
  val audioConfig = AudioConfig.newBuilder().setAudioEncoding(AudioEncoding.LINEAR16).build()
  val textToSpeechClient = TextToSpeechClient.create()

  def textToSpeech(text: String): List[Byte] =
    val synthesisInput = SynthesisInput.newBuilder().setText(text).build()
    val audioBytes = textToSpeechClient.synthesizeSpeech(
      synthesisInput,
      voiceSelectionParams,
      audioConfig
    ).getAudioContent()
     .readAllBytes()
    textToSpeechClient.close()