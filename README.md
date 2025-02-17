ScalaFx Speech
--------------
>ScalaFx text-to-speech ***prototype*** using ScalaFx, Os-Lib, Ox, Google Cloud Text-to-Speech and Scala 3.

>Naturally, the text is sourced from this world renown api: https://api.chucknorris.io/jokes/random :)

Google Account
--------------
>Due to the intrusive nature of a Google account, I chose not to setup an account.

>For local development:
1. Setup google account.
2. Install [Google CLI](https://cloud.google.com/sdk/docs/install)
3. Init [Google CLI](https://cloud.google.com/docs/authentication/set-up-adc-local-dev-environment)

Build
-----
1. sbt clean compile

Run
---
1. sbt run

Assembly
--------
1. sbt clean test assembly copyAssemblyJar

Execute
-------
1. java -jar .assembly/scalafx-speech-$version.jar ( or double-click executable jar )

Resources
---------
* [ScalaFx](https://www.scalafx.org/)

License
-------
>Copyright (c) [2025] [Objektwerks]

>Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    * http://www.apache.org/licenses/LICENSE-2.0

>Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.