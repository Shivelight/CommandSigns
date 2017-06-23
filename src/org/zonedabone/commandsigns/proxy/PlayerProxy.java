package org.zonedabone.commandsigns.proxy;

import java.net.InetSocketAddress;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.bukkit.Achievement;
import org.bukkit.BanList.Type;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.EntityEffect;
import org.bukkit.GameMode;
import org.bukkit.Instrument;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Note;
import org.bukkit.Particle;
import org.bukkit.Server;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.Statistic;
import org.bukkit.WeatherType;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.block.Block;
import org.bukkit.conversations.Conversation;
import org.bukkit.conversations.ConversationAbandonedEvent;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Egg;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Snowball;
import org.bukkit.entity.Villager;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.InventoryView.Property;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.MainHand;
import org.bukkit.inventory.Merchant;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.map.MapView;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.permissions.PermissionAttachmentInfo;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.util.Vector;

/**
 * Creates a tapped link between an originator Player and a recipient Player
 * originator and recipient can be the same player Allows sendMessage() methods
 * to be intercepted if the silent flag is set
 */
public class PlayerProxy implements Player {

	private Player originator;
	private Player recipient;
	boolean silent;

	public PlayerProxy(Player originator) {
		this(originator, originator, false);
	}

	public PlayerProxy(Player originator, boolean silent) {
		this(originator, originator, silent);
	}

	public PlayerProxy(Player originator, Player recipient) {
		this(originator, recipient, false);
	}

	public PlayerProxy(Player originator, Player recipient, boolean silent) {
		this.originator = originator;
		this.recipient = recipient;
		this.silent = silent;
	}

	public void abandonConversation(Conversation conversation) {
		originator.abandonConversation(conversation);
	}

	public void abandonConversation(Conversation conversation,
			ConversationAbandonedEvent details) {
		originator.abandonConversation(conversation, details);
	}

	public void acceptConversationInput(String input) {
		originator.acceptConversationInput(input);
	}

	public PermissionAttachment addAttachment(Plugin arg0) {
		return originator.addAttachment(arg0);
	}

	public PermissionAttachment addAttachment(Plugin arg0, int arg1) {
		return originator.addAttachment(arg0, arg1);
	}

	public PermissionAttachment addAttachment(Plugin arg0, String arg1,
			boolean arg2) {
		return originator.addAttachment(arg0, arg1, arg2);
	}

	public PermissionAttachment addAttachment(Plugin arg0, String arg1,
			boolean arg2, int arg3) {
		return originator.addAttachment(arg0, arg1, arg2, arg3);
	}

	public boolean addPotionEffect(PotionEffect effect) {
		return originator.addPotionEffect(effect);
	}

	public boolean addPotionEffect(PotionEffect effect, boolean force) {
		return originator.addPotionEffect(effect, force);
	}

	public boolean addPotionEffects(Collection<PotionEffect> effects) {
		return originator.addPotionEffects(effects);
	}

	public void awardAchievement(Achievement achievement) {
		originator.awardAchievement(achievement);
	}

	public boolean beginConversation(Conversation conversation) {
		return originator.beginConversation(conversation);
	}

	public boolean canSee(Player player) {
		return originator.canSee(player);
	}

	public void chat(String msg) {
		originator.chat(msg);
	}

	public void closeInventory() {
		originator.closeInventory();
	}

	public void damage(int amount) {
		originator.damage(amount);
	}

	public void damage(int amount, Entity source) {
		originator.damage(amount, source);
	}

	public boolean eject() {
		return originator.eject();
	}

	public Collection<PotionEffect> getActivePotionEffects() {
		return originator.getActivePotionEffects();
	}

	public InetSocketAddress getAddress() {
		return originator.getAddress();
	}

	public boolean getAllowFlight() {
		return originator.getAllowFlight();
	}

	public Location getBedSpawnLocation() {
		return originator.getBedSpawnLocation();
	}

	@Override
	public boolean getCanPickupItems() {
		return originator.getCanPickupItems();
	}

	public Location getCompassTarget() {
		return originator.getCompassTarget();
	}

	public String getDisplayName() {
		return originator.getDisplayName();
	}

	public Set<PermissionAttachmentInfo> getEffectivePermissions() {
		return originator.getEffectivePermissions();
	}

	public Inventory getEnderChest() {
		return originator.getEnderChest();
	}

	public int getEntityId() {
		return originator.getEntityId();
	}

	@Override
	public EntityEquipment getEquipment() {
		return originator.getEquipment();
	}

	public float getExhaustion() {
		return originator.getExhaustion();
	}

	public float getExp() {
		return originator.getExp();
	}

	public int getExpToLevel() {
		return originator.getExpToLevel();
	}

	public double getEyeHeight() {
		return originator.getEyeHeight();
	}

	public double getEyeHeight(boolean ignoreSneaking) {
		return originator.getEyeHeight(ignoreSneaking);
	}

	public Location getEyeLocation() {
		return originator.getEyeLocation();
	}

	public float getFallDistance() {
		return originator.getFallDistance();
	}

	public int getFireTicks() {
		return originator.getFireTicks();
	}

	public long getFirstPlayed() {
		return originator.getFirstPlayed();
	}

	public float getFlySpeed() {
		return originator.getFlySpeed();
	}

	public int getFoodLevel() {
		return originator.getFoodLevel();
	}

	public GameMode getGameMode() {
		return originator.getGameMode();
	}

	public double getHealth() {
		return originator.getHealth();
	}

	public PlayerInventory getInventory() {
		return originator.getInventory();
	}

	@SuppressWarnings("deprecation")
	public ItemStack getItemInHand() {
		return originator.getItemInHand();
	}

	public ItemStack getItemOnCursor() {
		return originator.getItemOnCursor();
	}

	public Player getKiller() {
		return originator.getKiller();
	}

	public double getLastDamage() {
		return originator.getLastDamage();
	}

	public EntityDamageEvent getLastDamageCause() {
		return originator.getLastDamageCause();
	}

	public long getLastPlayed() {
		return originator.getLastPlayed();
	}

	@Deprecated
	public List<Block> getLastTwoTargetBlocks(HashSet<Byte> transparent,
			int maxDistance) {
		return originator.getLastTwoTargetBlocks(transparent, maxDistance);
	}

	public int getLevel() {
		return originator.getLevel();
	}

	@Deprecated
	public List<Block> getLineOfSight(HashSet<Byte> transparent, int maxDistance) {
		return originator.getLineOfSight(transparent, maxDistance);
	}

	public Set<String> getListeningPluginChannels() {
		return originator.getListeningPluginChannels();
	}

	public Location getLocation() {
		return originator.getLocation();
	}

	@Override
	public Location getLocation(Location arg0) {
		return originator.getLocation(arg0);
	}

	public int getMaxFireTicks() {
		return originator.getMaxFireTicks();
	}

	public double getMaxHealth() {
		return originator.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();
	}

	public int getMaximumAir() {
		return originator.getMaximumAir();
	}

	public int getMaximumNoDamageTicks() {
		return originator.getMaximumNoDamageTicks();
	}

	public List<MetadataValue> getMetadata(String metadataKey) {
		return originator.getMetadata(metadataKey);
	}

	public String getName() {
		return originator.getName();
	}

	public List<Entity> getNearbyEntities(double x, double y, double z) {
		return originator.getNearbyEntities(x, y, z);
	}

	public int getNoDamageTicks() {
		return originator.getNoDamageTicks();
	}

	public InventoryView getOpenInventory() {
		return originator.getOpenInventory();
	}

	public Entity getPassenger() {
		return originator.getPassenger();
	}

	public Player getPlayer() {
		return originator.getPlayer();
	}

	public String getPlayerListName() {
		return originator.getPlayerListName();
	}

	public long getPlayerTime() {
		return originator.getPlayerTime();
	}

	public long getPlayerTimeOffset() {
		return originator.getPlayerTimeOffset();
	}

	public int getRemainingAir() {
		return originator.getRemainingAir();
	}

	@Override
	public boolean getRemoveWhenFarAway() {
		return originator.getRemoveWhenFarAway();
	}

	public float getSaturation() {
		return originator.getSaturation();
	}

	public Server getServer() {
		return originator.getServer();
	}

	public int getSleepTicks() {
		return originator.getSleepTicks();
	}

	@Deprecated
	public Block getTargetBlock(HashSet<Byte> transparent, int maxDistance) {
		return originator.getTargetBlock(transparent, maxDistance);
	}

	public int getTicksLived() {
		return originator.getTicksLived();
	}

	public int getTotalExperience() {
		return originator.getTotalExperience();
	}

	public EntityType getType() {
		return originator.getType();
	}

	public UUID getUniqueId() {
		return originator.getUniqueId();
	}

	public Entity getVehicle() {
		return originator.getVehicle();
	}

	public Vector getVelocity() {
		return originator.getVelocity();
	}

	public float getWalkSpeed() {
		return originator.getWalkSpeed();
	}

	public World getWorld() {
		return originator.getWorld();
	}

	public void giveExp(int amount) {
		originator.giveExp(amount);
	}

	@Override
	public void giveExpLevels(int arg0) {
		originator.giveExpLevels(arg0);
	}

	public boolean hasLineOfSight(Entity arg0) {
		return originator.hasLineOfSight(arg0);
	}

	public boolean hasMetadata(String metadataKey) {
		return originator.hasMetadata(metadataKey);
	}

	public boolean hasPermission(Permission arg0) {
		return originator.hasPermission(arg0);
	}

	public boolean hasPermission(String arg0) {
		return originator.hasPermission(arg0);
	}

	public boolean hasPlayedBefore() {
		return originator.hasPlayedBefore();
	}

	public boolean hasPotionEffect(PotionEffectType type) {
		return originator.hasPotionEffect(type);
	}

	public void hidePlayer(Player player) {
		originator.hidePlayer(player);
	}

	public void incrementStatistic(Statistic statistic) {
		originator.incrementStatistic(statistic);
	}

	public void incrementStatistic(Statistic statistic, int amount) {
		originator.incrementStatistic(statistic, amount);
	}

	public void incrementStatistic(Statistic statistic, Material material) {
		originator.incrementStatistic(statistic, material);
	}

	public void incrementStatistic(Statistic statistic, Material material,
			int amount) {
		originator.incrementStatistic(statistic, material, amount);
	}

	public boolean isBanned() {
		return originator.isBanned();
	}

	public boolean isBlocking() {
		return originator.isBlocking();
	}

	public boolean isConversing() {
		return originator.isConversing();
	}

	public boolean isDead() {
		return originator.isDead();
	}

	public boolean isEmpty() {
		return originator.isEmpty();
	}

	public boolean isFlying() {
		return originator.isFlying();
	}

	public boolean isInsideVehicle() {
		return originator.isInsideVehicle();
	}

	public boolean isOnline() {
		return originator.isOnline();
	}

	public boolean isOp() {
		return originator.isOp();
	}

	public boolean isPermissionSet(Permission arg0) {
		return originator.isPermissionSet(arg0);
	}

	public boolean isPermissionSet(String arg0) {
		return originator.isPermissionSet(arg0);
	}

	public boolean isPlayerTimeRelative() {
		return originator.isPlayerTimeRelative();
	}

	public boolean isSilent() {
		return silent;
	}

	public boolean isSleeping() {
		return originator.isSleeping();
	}

	public boolean isSleepingIgnored() {
		return originator.isSleepingIgnored();
	}

	public boolean isSneaking() {
		return originator.isSneaking();
	}

	public boolean isSprinting() {
		return originator.isSprinting();
	}

	public boolean isValid() {
		return originator.isValid();
	}

	public boolean isWhitelisted() {
		return originator.isWhitelisted();
	}

	public void kickPlayer(String message) {
		originator.kickPlayer(message);
	}

	public <T extends Projectile> T launchProjectile(
			Class<? extends T> projectile) {
		return originator.launchProjectile(projectile);
	}

	public boolean leaveVehicle() {
		return originator.leaveVehicle();
	}

	public void loadData() {
		originator.loadData();
	}

	public InventoryView openEnchanting(Location location, boolean force) {
		return originator.openEnchanting(location, force);
	}

	public InventoryView openInventory(Inventory inventory) {
		return originator.openInventory(inventory);
	}

	public void openInventory(InventoryView inventory) {
		originator.openInventory(inventory);
	}

	public InventoryView openWorkbench(Location location, boolean force) {
		return originator.openWorkbench(location, force);
	}

	public boolean performCommand(String command) {
		return originator.performCommand(command);
	}

	public void playEffect(EntityEffect type) {
		originator.playEffect(type);
	}

	@Deprecated
	public void playEffect(Location loc, Effect effect, int data) {
		originator.playEffect(loc, effect, data);
	}

	public <T> void playEffect(Location loc, Effect effect, T data) {
		originator.playEffect(loc, effect, data);
	}

	@Deprecated
	public void playNote(Location loc, byte instrument, byte note) {
		originator.playNote(loc, instrument, note);
	}

	public void playNote(Location loc, Instrument instrument, Note note) {
		originator.playNote(loc, instrument, note);
	}

	public void playSound(Location arg0, Sound arg1, float arg2, float arg3) {
		originator.playSound(arg0, arg1, arg2, arg3);
	}

	public void recalculatePermissions() {
		originator.recalculatePermissions();
	}

	public void remove() {
		originator.remove();
	}

	public void removeAttachment(PermissionAttachment arg0) {
		originator.removeAttachment(arg0);
	}

	public void removeMetadata(String metadataKey, Plugin owningPlugin) {
		originator.removeMetadata(metadataKey, owningPlugin);
	}

	public void removePotionEffect(PotionEffectType type) {
		originator.removePotionEffect(type);
	}

	@Override
	public void resetMaxHealth() {
		originator.resetMaxHealth();
	}

	public void resetPlayerTime() {
		originator.resetPlayerTime();
	}

	public void saveData() {
		originator.saveData();
	}

	@Deprecated
	public void sendBlockChange(Location loc, int material, byte data) {
		originator.sendBlockChange(loc, material, data);
	}

	@Deprecated
	public void sendBlockChange(Location loc, Material material, byte data) {
		originator.sendBlockChange(loc, material, data);
	}

	@Deprecated
	public boolean sendChunkChange(Location loc, int sx, int sy, int sz,
			byte[] data) {
		return originator.sendChunkChange(loc, sx, sy, sz, data);
	}

	public void sendMap(MapView map) {
		originator.sendMap(map);
	}

	public void sendMessage(String message) {
		if (!silent && recipient != null)
			recipient.sendMessage(message);
	}

	public void sendMessage(String[] messages) {
		if (!silent && recipient != null)
			recipient.sendMessage(messages);
	}

	public void sendPluginMessage(Plugin source, String channel, byte[] message) {
		originator.sendPluginMessage(source, channel, message);
	}

	public void sendRawMessage(String message) {
		if (!silent && recipient != null)
			recipient.sendRawMessage(message);
	}

	public Map<String, Object> serialize() {
		return originator.serialize();
	}

	public void setAllowFlight(boolean flight) {
		originator.setAllowFlight(flight);
	}

	public void setBanned(boolean banned) {
//		originator.setBanned(banned);
		Bukkit.getBanList(Type.NAME).addBan(originator.getName(), null, null, null);
	}

	public void setBedSpawnLocation(Location location) {
		originator.setBedSpawnLocation(location);
	}

	@Override
	public void setBedSpawnLocation(Location arg0, boolean arg1) {
		originator.setBedSpawnLocation(arg0, arg1);
	}

	@Override
	public void setCanPickupItems(boolean arg0) {
		originator.setCanPickupItems(arg0);
	}

	public void setCompassTarget(Location loc) {
		originator.setCompassTarget(loc);
	}

	public void setDisplayName(String name) {
		originator.setDisplayName(name);
	}

	public void setExhaustion(float value) {
		originator.setExhaustion(value);
	}

	public void setExp(float exp) {
		originator.setExp(exp);
	}

	public void setFallDistance(float distance) {
		originator.setFallDistance(distance);
	}

	public void setFireTicks(int ticks) {
		originator.setFireTicks(ticks);
	}

	public void setFlying(boolean value) {
		originator.setFlying(value);
	}

	public void setFlySpeed(float arg0) throws IllegalArgumentException {
		originator.setFlySpeed(arg0);
	}

	public void setFoodLevel(int value) {
		originator.setFoodLevel(value);
	}

	public void setGameMode(GameMode mode) {
		originator.setGameMode(mode);
	}

	public void setHealth(int health) {
		originator.setHealth(health);
	}

	public void setItemInHand(ItemStack item) {
//		originator.setItemInHand(item);
		originator.getInventory().setItemInMainHand(item);
	}

	public void setItemOnCursor(ItemStack item) {
		originator.setItemOnCursor(item);
	}

	public void setLastDamage(int damage) {
		originator.setLastDamage(damage);
	}

	public void setLastDamageCause(EntityDamageEvent event) {
		originator.setLastDamageCause(event);
	}

	public void setLevel(int level) {
		originator.setLevel(level);
	}

	public void setMaximumAir(int ticks) {
		originator.setMaximumAir(ticks);
	}

	public void setMaximumNoDamageTicks(int ticks) {
		originator.setMaximumNoDamageTicks(ticks);
	}

	public void setMetadata(String metadataKey, MetadataValue newMetadataValue) {
		originator.setMetadata(metadataKey, newMetadataValue);
	}

	public void setNoDamageTicks(int ticks) {
		originator.setNoDamageTicks(ticks);
	}

	public void setOp(boolean arg0) {
		originator.setOp(arg0);
	}

//	public boolean setPassenger(Entity passenger) {
//		return originator.setPassenger(passenger);
//	}

	public void setPlayerListName(String name) {
		originator.setPlayerListName(name);
	}

	public void setPlayerTime(long time, boolean relative) {
		originator.setPlayerTime(time, relative);
	}

	public void setRemainingAir(int ticks) {
		originator.setRemainingAir(ticks);
	}

	@Override
	public void setRemoveWhenFarAway(boolean arg0) {
		originator.setRemoveWhenFarAway(arg0);
	}

	public void setSaturation(float value) {
		originator.setSaturation(value);
	}

	public void setSilent(boolean silent) {
		this.silent = silent;
	}

	public void setSleepingIgnored(boolean isSleeping) {
		originator.setSleepingIgnored(isSleeping);
	}

	public void setSneaking(boolean sneak) {
		originator.setSneaking(sneak);
	}

	public void setSprinting(boolean sprinting) {
		originator.setSprinting(sprinting);
	}

	@Deprecated
	@Override
	public void setTexturePack(String arg0) {
		originator.setTexturePack(arg0);
	}

	public void setTicksLived(int value) {
		originator.setTicksLived(value);
	}

	public void setTotalExperience(int exp) {
		originator.setTotalExperience(exp);
	}

	public void setVelocity(Vector velocity) {
		originator.setVelocity(velocity);
	}

	public void setWalkSpeed(float arg0) throws IllegalArgumentException {
		originator.setWalkSpeed(arg0);
	}

	public void setWhitelisted(boolean value) {
		originator.setWhitelisted(value);
	}

	public boolean setWindowProperty(Property prop, int value) {
		return originator.setWindowProperty(prop, value);
	}

	@Deprecated
	public Arrow shootArrow() {
		return originator.launchProjectile(Arrow.class);
	}

	public void showPlayer(Player player) {
		originator.showPlayer(player);
	}

	public boolean teleport(Entity destination) {
		return originator.teleport(destination);
	}

	public boolean teleport(Entity destination, TeleportCause cause) {
		return originator.teleport(destination, cause);
	}

	public boolean teleport(Location location) {
		return originator.teleport(location);
	}

	public boolean teleport(Location location, TeleportCause cause) {
		return originator.teleport(location, cause);
	}

	@Deprecated
	public Egg throwEgg() {
		return originator.launchProjectile(Egg.class);
	}

	@Deprecated
	public Snowball throwSnowball() {
		return originator.launchProjectile(Snowball.class);
	}

	@Deprecated
	public void updateInventory() {
		originator.updateInventory();
	}

    @Override
    public String getCustomName() {
        return originator.getCustomName();
    }

    @Override
    public boolean isCustomNameVisible() {
        return originator.isCustomNameVisible();
    }

    @Override
    public void setCustomName(String arg0) {
        originator.setCustomName(arg0);
    }

    @Override
    public void setCustomNameVisible(boolean arg0) {
        originator.setCustomNameVisible(arg0);
    }

    @Override
    public WeatherType getPlayerWeather() {
        return originator.getPlayerWeather();
    }

    @Override
    @Deprecated
    public boolean isOnGround() {
        return originator.isOnGround();
    }

    @Override
    public void resetPlayerWeather() {
        originator.resetPlayerWeather();
    }

    @Override
    public void setPlayerWeather(WeatherType arg0) {
        originator.setPlayerWeather(arg0);
    }

    @Override
    public Scoreboard getScoreboard() {
        return originator.getScoreboard();
    }

    @Override
    public void setScoreboard(Scoreboard arg0) throws IllegalArgumentException, IllegalStateException {
        originator.setScoreboard(arg0);
    }

    @Override
    @Deprecated
    public int _INVALID_getLastDamage() {
        return originator._INVALID_getLastDamage();
    }

    @Override
    @Deprecated
    public void _INVALID_setLastDamage(int arg0) {
        originator._INVALID_setLastDamage(arg0);
    }

    @Override
    public Entity getLeashHolder() throws IllegalStateException {
        return originator.getLeashHolder();
    }

    @Override
    public boolean isLeashed() {
        return originator.isLeashed();
    }

    @Override
    public void setLastDamage(double arg0) {
        originator.setLastDamage(arg0);
    }

    @Override
    public boolean setLeashHolder(Entity arg0) {
        return originator.setLeashHolder(arg0);
    }

    @Override
    @Deprecated
    public void _INVALID_damage(int arg0) {
        originator._INVALID_damage(arg0);
    }

    @Override
    @Deprecated
    public void _INVALID_damage(int arg0, Entity arg1) {
        originator._INVALID_damage(arg0, arg1);
    }

    @Override
    @Deprecated
    public int _INVALID_getHealth() {
        return originator._INVALID_getHealth();
    }

    @Override
    @Deprecated
    public int _INVALID_getMaxHealth() {
        return originator._INVALID_getMaxHealth();
    }

    @Override
    @Deprecated
    public void _INVALID_setHealth(int arg0) {
        originator._INVALID_setHealth(arg0);
    }

    @Override
    @Deprecated
    public void _INVALID_setMaxHealth(int arg0) {
        originator._INVALID_setMaxHealth(arg0);
    }

    @Override
    public void damage(double arg0) {
        originator.damage(arg0);
    }

    @Override
    public void damage(double arg0, Entity arg1) {
        originator.damage(arg0, arg1);
    }

    @Override
    public void setHealth(double arg0) {
        originator.setHealth(arg0);
    }

    @Override
    public void setMaxHealth(double arg0) {
        originator.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(arg0);
    }

    @Override
    public double getHealthScale() {
        return originator.getHealthScale();
    }

    @Override
    public boolean isHealthScaled() {
        return originator.isHealthScaled();
    }

    @Override
    @Deprecated
    public void playSound(Location arg0, String arg1, float arg2, float arg3) {
        originator.playSound(arg0, arg1, arg2, arg3);
    }

    @Override
    public void setHealthScale(double arg0) throws IllegalArgumentException {
        originator.setHealthScale(arg0);
    }

    @Override
    public void setHealthScaled(boolean arg0) {
        originator.setHealthScaled(arg0);
    }

    @Override
    public void setResourcePack(String arg0) {
        originator.setResourcePack(arg0);
    }

	@Override
	public int getCooldown(Material arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public MainHand getMainHand() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasCooldown(Material arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isHandRaised() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public InventoryView openMerchant(Villager arg0, boolean arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InventoryView openMerchant(Merchant arg0, boolean arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setCooldown(Material arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Block> getLastTwoTargetBlocks(Set<Material> arg0, int arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Block> getLineOfSight(Set<Material> arg0, int arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PotionEffect getPotionEffect(PotionEffectType arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Block getTargetBlock(Set<Material> arg0, int arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasAI() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCollidable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isGliding() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setAI(boolean arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setCollidable(boolean arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setGliding(boolean arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public AttributeInstance getAttribute(Attribute arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addPassenger(Entity arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addScoreboardTag(String arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public double getHeight() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Entity> getPassengers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getPortalCooldown() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Set<String> getScoreboardTags() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getWidth() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean hasGravity() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isGlowing() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isInvulnerable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removePassenger(Entity arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeScoreboardTag(String arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setGlowing(boolean arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setGravity(boolean arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setInvulnerable(boolean arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean setPassenger(Entity arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setPortalCooldown(int arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <T extends Projectile> T launchProjectile(Class<? extends T> arg0, Vector arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void decrementStatistic(Statistic arg0) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void decrementStatistic(Statistic arg0, int arg1) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void decrementStatistic(Statistic arg0, Material arg1) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void decrementStatistic(Statistic arg0, EntityType arg1) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void decrementStatistic(Statistic arg0, Material arg1, int arg2) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void decrementStatistic(Statistic arg0, EntityType arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Entity getSpectatorTarget() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getStatistic(Statistic arg0) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getStatistic(Statistic arg0, Material arg1) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getStatistic(Statistic arg0, EntityType arg1) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return 0;
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean hasAchievement(Achievement arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void incrementStatistic(Statistic arg0, EntityType arg1) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void incrementStatistic(Statistic arg0, EntityType arg1, int arg2) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void playSound(Location arg0, Sound arg1, SoundCategory arg2, float arg3, float arg4) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void playSound(Location arg0, String arg1, SoundCategory arg2, float arg3, float arg4) {
		// TODO Auto-generated method stub
		
	}

	@SuppressWarnings("deprecation")
	@Override
	public void removeAchievement(Achievement arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resetTitle() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendSignChange(Location arg0, String[] arg1) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendTitle(String arg0, String arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendTitle(String arg0, String arg1, int arg2, int arg3, int arg4) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setResourcePack(String arg0, byte[] arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setSpectatorTarget(Entity arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setStatistic(Statistic arg0, int arg1) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setStatistic(Statistic arg0, Material arg1, int arg2) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setStatistic(Statistic arg0, EntityType arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void spawnParticle(Particle arg0, Location arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <T> void spawnParticle(Particle arg0, Location arg1, int arg2, T arg3) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void spawnParticle(Particle arg0, double arg1, double arg2, double arg3, int arg4) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <T> void spawnParticle(Particle arg0, double arg1, double arg2, double arg3, int arg4, T arg5) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void spawnParticle(Particle arg0, Location arg1, int arg2, double arg3, double arg4, double arg5) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <T> void spawnParticle(Particle arg0, Location arg1, int arg2, double arg3, double arg4, double arg5,
			T arg6) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void spawnParticle(Particle arg0, Location arg1, int arg2, double arg3, double arg4, double arg5,
			double arg6) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void spawnParticle(Particle arg0, double arg1, double arg2, double arg3, int arg4, double arg5, double arg6,
			double arg7) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <T> void spawnParticle(Particle arg0, Location arg1, int arg2, double arg3, double arg4, double arg5,
			double arg6, T arg7) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <T> void spawnParticle(Particle arg0, double arg1, double arg2, double arg3, int arg4, double arg5,
			double arg6, double arg7, T arg8) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void spawnParticle(Particle arg0, double arg1, double arg2, double arg3, int arg4, double arg5, double arg6,
			double arg7, double arg8) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <T> void spawnParticle(Particle arg0, double arg1, double arg2, double arg3, int arg4, double arg5,
			double arg6, double arg7, double arg8, T arg9) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Spigot spigot() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void stopSound(Sound arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stopSound(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stopSound(Sound arg0, SoundCategory arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stopSound(String arg0, SoundCategory arg1) {
		// TODO Auto-generated method stub
		
	}

}
