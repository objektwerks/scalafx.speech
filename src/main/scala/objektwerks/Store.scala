package objektwerks

import com.typesafe.scalalogging.LazyLogging

import os.Path

import ox.*

import scalafx.application.Platform

final class Store extends LazyLogging:
  os.makeDir.all( buildRecipesPath )

  private val recipesPath = buildRecipesPath

  logger.info("Initialized store.")

  private def buildRecipesPath: Path = os.home / ".brewmeister" / "store" / "recipes"

  private def assertNotInFxThread: Unit = assert( !Platform.isFxApplicationThread, "Store operation called on Fx thread!" )

  def listRecipes: List[Recipe] =
    supervised:
      assertNotInFxThread
      os.list(recipesPath)
        .filter { path => path.baseName.nonEmpty }
        .map { path => readRecipe(s"${path.baseName}.json") }.toList

  def writeRecipe(recipe: Recipe): Unit =
    supervised:
      assertNotInFxThread
      val recipeAsJson = writeJson(recipe)
      os.write.over(recipesPath / recipe.fileProperty.value, recipeAsJson)
      logger.info(s"Write recipe: ${recipe.name}")

  def removeRecipe(recipe: Recipe): Unit =
    supervised:
      assertNotInFxThread
      os.remove(recipesPath / recipe.fileProperty.value)
      logger.info(s"Remove recipe: ${recipe.name}")