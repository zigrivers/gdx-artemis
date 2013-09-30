package com.artemis.managers;

import com.artemis.Entity;
import com.artemis.utils.SafeArray;
import com.badlogic.gdx.utils.ObjectMap;

/**
 * If you need to group your entities together, e.g. tanks going into "units" group or explosions into "effects",
 * then use this manager. You must retrieve it using world instance.
 * 
 * A entity can be assigned to more than one group.
 * 
 * @author Arni Arent
 *
 */
public class GroupManager extends Manager {
    protected ObjectMap<String, SafeArray<Entity>> entitiesByGroup;
    protected ObjectMap<Entity, SafeArray<String>> groupsByEntity;

    public GroupManager() {
        entitiesByGroup = new ObjectMap<String, SafeArray<Entity>>();
        groupsByEntity = new ObjectMap<Entity, SafeArray<String>>();
    }

    /**
     * Set the group of the entity.
     * 
     * @param group group to add the entity into.
     * @param e entity to add into the group.
     */
    public void add(Entity e, String group) {
        SafeArray<Entity> entities = entitiesByGroup.get(group);
        if(entities == null) {
            entities = new SafeArray<Entity>();
            entitiesByGroup.put(group, entities);
        }
        entities.add(e);

        SafeArray<String> groups = groupsByEntity.get(e);
        if(groups == null) {
            groups = new SafeArray<String>();
            groupsByEntity.put(e, groups);
        }
        groups.add(group);
    }

    /**
     * Remove the entity from the specified group.
     * @param e
     * @param group
     */
    public void remove(Entity e, String group) {
        SafeArray<Entity> entities = entitiesByGroup.get(group);
        if(entities != null) {
            entities.removeValue(e, true);
        }

        SafeArray<String> groups = groupsByEntity.get(e);
        if(groups != null) {
            groups.removeValue(group, true);
        }
    }

    public void removeFromAllGroups(Entity e) {
        SafeArray<String> groups = groupsByEntity.get(e);
        if(groups != null) {
            for(int i = 0; groups.size > i; i++) {
                SafeArray<Entity> entities = entitiesByGroup.get(groups.get(i));
                if(entities != null) {
                    entities.removeValue(e, true);
                }
            }
            groups.clear();
        }
    }

    /**
     * Get all entities that belong to the provided group.
     * @param group name of the group.
     * @return read-only Array of entities belonging to the group.
     */
    public SafeArray<Entity> getEntities(String group) {
        SafeArray<Entity> entities = entitiesByGroup.get(group);
        if(entities == null) {
            entities = new SafeArray<Entity>();
            entitiesByGroup.put(group, entities);
        }
        return entities;
    }

    /**
     * @param e entity
     * @return the groups the entity belongs to, null if none.
     */
    public SafeArray<String> getGroups(Entity e) {
        return groupsByEntity.get(e);
    }

    /**
     * Checks if the entity belongs to any group.
     * @param e the entity to check.
     * @return true if it is in any group, false if none.
     */
    public boolean isInAnyGroup(Entity e) {
        return getGroups(e) != null;
    }

    /**
     * Check if the entity is in the supplied group.
     * @param group the group to check in.
     * @param e the entity to check for.
     * @return true if the entity is in the supplied group, false if not.
     */
    public boolean isInGroup(Entity e, String group) {
        if(group != null) {
            SafeArray<String> groups = groupsByEntity.get(e);
            for(int i = 0; groups.size > i; i++) {
                String g = groups.get(i);
                if(group == g || group.equals(g)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void deleted(Entity e) {
        removeFromAllGroups(e);
    }

}
