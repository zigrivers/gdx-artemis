
# Version 0.2
 - Created an event system to allow inter-system communication.
 - Speed improvement: Removed some unnecessary accessor calls.
 - Deprecated Mapper annotation and imporved ComoponentMapper intialization
 - Allow inheritance for EntitySystems (component mappers are initialized in parent objects as well)
 - Fixed a bug in EntitySystem.check(e) and made it more performant.
 - Adding ComponentMapper reuse. No need to create new instances for all the systems.
 - Adding @IgnoreMapper annotation to allow bypassing ComponentMapper initialization during world initialization. (useful for generic systems with generic mappers, where reflection won't work)
 - Adding a notion of stopping event propagation by marking event handled.
 - Making a dent in unit testing.

# Version 0.1

 - Some files have been reshuffled in the packages for better organization.
 - Most private fields and methods have been changed to protected to allow for easier extension.
 - Component is now an interface.
 - ComponentType has been removed. The indexing done in the ComponentManager.
 - Bag and ImmutableBag have been removed, in favor of a slightly modified com.badlogic.gdx.utils.Array.
 - HashMaps have been replaced by com.badlogic.gdx.utils.ObjectMap. ObjectIntMap and IntArray are used where appropriate.
 - Starting to add more Javadoc to classes.
 - No more classes instantiated every time World.process is run.
 - IdentifierPool has been moved to a separate class.
 - Removed all the unused utils classes.
 - Added a check to EntitySystem to prune activeEntities before processing. (in case of removal by a different system earlier on in the process cycle)
 - Cleaned up some compiler warnings.
 - Mavenize the project
