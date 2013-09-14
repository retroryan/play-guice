package services

import javax.inject.Singleton

/**
 * A type declaring the interface that will be injectable.
 */
abstract class TextGenerator(val welcomeText: String)

/**
 * A simple implementation of TextGenerator that we will inject.  This is what will be used for production
 */
@Singleton
class WelcomeTextGenerator extends TextGenerator("Your new application is ready.")


/**
 * A simple implementation of TextGenerator that we will inject.   This is used for development
 */
@Singleton
class DevTextGenerator extends TextGenerator("Dev Text is ready.")