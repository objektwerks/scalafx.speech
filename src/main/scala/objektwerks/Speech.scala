package objektwerks

import com.google.cloud.texttospeech.v1.{AudioConfig, AudioEncoding, SynthesisInput, TextToSpeechClient, VoiceSelectionParams}

import java.io.IOException

final class Speech:
  val voiceSelectionParams = VoiceSelectionParams
    .newBuilder()
    .setLanguageCode("en-US")
    .build()
  val audioConfig = AudioConfig
    .newBuilder()
    .setAudioEncoding(AudioEncoding.LINEAR16)
    .build()

  def textToSpeech(text: String): Either[IOException, Array[Byte]] =
    val synthesisInput = SynthesisInput
      .newBuilder()
      .setText(text)
      .build()
    val textToSpeechClient = TextToSpeechClient.create()
    try
      val bytes = textToSpeechClient.synthesizeSpeech(
        synthesisInput,
        voiceSelectionParams,
        audioConfig
      ).getAudioContent()
       .toByteArray()
      Right(bytes)
    catch
      case error: IOException => Left(error)
    finally
      textToSpeechClient.close()