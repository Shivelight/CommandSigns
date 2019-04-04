package org.zonedabone.commandsigns.proxy;

import java.net.InetSocketAddress;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import org.bukkit.Achievement;
import org.bukkit.BanList.Type;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.EntityEffect;
import org.bukkit.FluidCollisionMode;
import org.bukkit.GameMode;
import org.bukkit.Instrument;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Note;
import org.bukkit.Particle;
import org.bukkit.Server;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.Statistic;
import org.bukkit.WeatherType;
import org.bukkit.World;
import org.bukkit.advancement.Advancement;
import org.bukkit.advancement.AdvancementProgress;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.PistonMoveReaction;
import org.bukkit.block.data.BlockData;
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
import org.bukkit.util.BoundingBox;
import org.bukkit.util.RayTraceResult;
import org.bukkit.util.Vector;

/**
 * Creates a tapped link between an originator Player and a recipient Player originator and
 * recipient can be the same player Allows sendMessage() methods to be intercepted if the silent
 * flag is set
 */

@SuppressWarnings("deprecation")
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

  public void abandonConversation(Conversation conversation, ConversationAbandonedEvent details) {
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

  public PermissionAttachment addAttachment(Plugin arg0, String arg1, boolean arg2) {
    return originator.addAttachment(arg0, arg1, arg2);
  }

  public PermissionAttachment addAttachment(Plugin arg0, String arg1, boolean arg2, int arg3) {
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

  public List<Block> getLastTwoTargetBlocks(Set<Material> transparent, int maxDistance) {
    return originator.getLastTwoTargetBlocks(transparent, maxDistance);
  }

  public int getLevel() {
    return originator.getLevel();
  }

  public List<Block> getLineOfSight(Set<Material> transparent, int maxDistance) {
    return originator.getLineOfSight(transparent, maxDistance);
  }

  public Set<String> getListeningPluginChannels() {
    return originator.getListeningPluginChannels();
  }

  public Location getLocation() {
    return originator.getLocation();
  }

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

  public Block getTargetBlock(Set<Material> transparent, int maxDistance) {
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

  public void incrementStatistic(Statistic statistic, Material material, int amount) {
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

  public <T extends Projectile> T launchProjectile(Class<? extends T> projectile) {
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

  @Deprecated
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
  public void sendBlockChange(Location loc, Material material, byte data) {
    originator.sendBlockChange(loc, material, data);
  }

  @Deprecated
  public boolean sendChunkChange(Location loc, int sx, int sy, int sz, byte[] data) {
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
    // originator.setBanned(banned);
    Bukkit.getBanList(Type.NAME).addBan(originator.getName(), null, null, null);
  }

  public void setBedSpawnLocation(Location location) {
    originator.setBedSpawnLocation(location);
  }

  public void setBedSpawnLocation(Location arg0, boolean arg1) {
    originator.setBedSpawnLocation(arg0, arg1);
  }

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
    // originator.setItemInHand(item);
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

  public boolean setPassenger(Entity passenger) {
    return originator.setPassenger(passenger);
  }

  public void setPlayerListName(String name) {
    originator.setPlayerListName(name);
  }

  public void setPlayerTime(long time, boolean relative) {
    originator.setPlayerTime(time, relative);
  }

  public void setRemainingAir(int ticks) {
    originator.setRemainingAir(ticks);
  }

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

  public String getCustomName() {
    return originator.getCustomName();
  }

  public boolean isCustomNameVisible() {
    return originator.isCustomNameVisible();
  }

  public void setCustomName(String arg0) {
    originator.setCustomName(arg0);
  }

  public void setCustomNameVisible(boolean arg0) {
    originator.setCustomNameVisible(arg0);
  }

  public WeatherType getPlayerWeather() {
    return originator.getPlayerWeather();
  }

  @Deprecated
  public boolean isOnGround() {
    return originator.isOnGround();
  }

  public void resetPlayerWeather() {
    originator.resetPlayerWeather();
  }

  public void setPlayerWeather(WeatherType arg0) {
    originator.setPlayerWeather(arg0);
  }

  public Scoreboard getScoreboard() {
    return originator.getScoreboard();
  }

  public void setScoreboard(Scoreboard arg0)
      throws IllegalArgumentException, IllegalStateException {
    originator.setScoreboard(arg0);
  }

  public Entity getLeashHolder() throws IllegalStateException {
    return originator.getLeashHolder();
  }

  public boolean isLeashed() {
    return originator.isLeashed();
  }

  public void setLastDamage(double arg0) {
    originator.setLastDamage(arg0);
  }

  public boolean setLeashHolder(Entity arg0) {
    return originator.setLeashHolder(arg0);
  }

  public void damage(double arg0) {
    originator.damage(arg0);
  }

  public void damage(double arg0, Entity arg1) {
    originator.damage(arg0, arg1);
  }

  public void setHealth(double arg0) {
    originator.setHealth(arg0);
  }

  public void setMaxHealth(double arg0) {
    originator.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(arg0);
  }

  public double getHealthScale() {
    return originator.getHealthScale();
  }

  public boolean isHealthScaled() {
    return originator.isHealthScaled();
  }

  @Deprecated
  public void playSound(Location arg0, String arg1, float arg2, float arg3) {
    originator.playSound(arg0, arg1, arg2, arg3);
  }

  public void setHealthScale(double arg0) throws IllegalArgumentException {
    originator.setHealthScale(arg0);
  }

  public void setHealthScaled(boolean arg0) {
    originator.setHealthScaled(arg0);
  }

  public void setResourcePack(String arg0) {
    originator.setResourcePack(arg0);
  }

  public boolean discoverRecipe(NamespacedKey recipe) {
    return false;
  }

  public int discoverRecipes(Collection<NamespacedKey> recipes) {
    return 0;
  }

  public int getCooldown(Material material) {
    return 0;
  }

  public MainHand getMainHand() {
    return null;
  }

  public Entity getShoulderEntityLeft() {
    return null;
  }

  public Entity getShoulderEntityRight() {
    return null;
  }

  public boolean hasCooldown(Material material) {
    return false;
  }

  public boolean isHandRaised() {
    return false;
  }

  public InventoryView openMerchant(Villager trader, boolean force) {
    return null;
  }

  public InventoryView openMerchant(Merchant merchant, boolean force) {
    return null;
  }

  public void setCooldown(Material material, int ticks) {

  }

  public void setShoulderEntityLeft(Entity entity) {

  }

  public void setShoulderEntityRight(Entity entity) {

  }

  public boolean undiscoverRecipe(NamespacedKey recipe) {
    return false;
  }

  public int undiscoverRecipes(Collection<NamespacedKey> recipes) {
    return 0;
  }

  public PotionEffect getPotionEffect(PotionEffectType type) {
    return null;
  }

  public Block getTargetBlockExact(int maxDistance) {
    return null;
  }

  public Block getTargetBlockExact(int maxDistance, FluidCollisionMode fluidCollisionMode) {
    return null;
  }

  public boolean hasAI() {
    return false;
  }

  public boolean isCollidable() {
    return false;
  }

  public boolean isGliding() {
    return false;
  }

  public boolean isRiptiding() {
    return false;
  }

  public boolean isSwimming() {
    return false;
  }

  public RayTraceResult rayTraceBlocks(double maxDistance) {
    return null;
  }

  public RayTraceResult rayTraceBlocks(double maxDistance, FluidCollisionMode fluidCollisionMode) {
    return null;
  }

  public void setAI(boolean ai) {

  }

  public void setCollidable(boolean collidable) {

  }

  public void setGliding(boolean gliding) {

  }

  public void setSwimming(boolean swimming) {

  }

  public AttributeInstance getAttribute(Attribute attribute) {
    return null;
  }

  public boolean addPassenger(Entity passenger) {
    return false;
  }

  public boolean addScoreboardTag(String tag) {
    return false;
  }

  public BoundingBox getBoundingBox() {
    return null;
  }

  public BlockFace getFacing() {
    return null;
  }

  public double getHeight() {
    return 0;
  }

  public List<Entity> getPassengers() {
    return null;
  }

  public PistonMoveReaction getPistonMoveReaction() {
    return null;
  }

  public int getPortalCooldown() {
    return 0;
  }

  public Set<String> getScoreboardTags() {
    return null;
  }

  public double getWidth() {
    return 0;
  }

  public boolean hasGravity() {
    return false;
  }

  public boolean isGlowing() {
    return false;
  }

  public boolean isInvulnerable() {
    return false;
  }

  public boolean isPersistent() {
    return false;
  }

  public boolean removePassenger(Entity passenger) {
    return false;
  }

  public boolean removeScoreboardTag(String tag) {
    return false;
  }

  public void setGlowing(boolean flag) {

  }

  public void setGravity(boolean gravity) {

  }

  public void setInvulnerable(boolean flag) {

  }

  public void setPersistent(boolean persistent) {

  }

  public void setPortalCooldown(int cooldown) {

  }

  public <T extends Projectile> T launchProjectile(Class<? extends T> projectile, Vector velocity) {
    return null;
  }

  public void decrementStatistic(Statistic statistic) throws IllegalArgumentException {

  }

  public void decrementStatistic(Statistic statistic, int amount) throws IllegalArgumentException {

  }

  public void decrementStatistic(Statistic statistic, Material material)
      throws IllegalArgumentException {

  }

  public void decrementStatistic(Statistic statistic, EntityType entityType)
      throws IllegalArgumentException {

  }

  public void decrementStatistic(Statistic statistic, Material material, int amount)
      throws IllegalArgumentException {

  }

  public void decrementStatistic(Statistic statistic, EntityType entityType, int amount) {

  }

  public AdvancementProgress getAdvancementProgress(Advancement advancement) {
    return null;
  }

  public int getClientViewDistance() {
    return 0;
  }

  public String getLocale() {
    return null;
  }

  public String getPlayerListFooter() {
    return null;
  }

  public String getPlayerListHeader() {
    return null;
  }

  public Entity getSpectatorTarget() {
    return null;
  }

  public int getStatistic(Statistic statistic) throws IllegalArgumentException {
    return 0;
  }

  public int getStatistic(Statistic statistic, Material material) throws IllegalArgumentException {
    return 0;
  }

  public int getStatistic(Statistic statistic, EntityType entityType)
      throws IllegalArgumentException {
    return 0;
  }

  public boolean hasAchievement(Achievement achievement) {
    return false;
  }

  public void hidePlayer(Plugin plugin, Player player) {

  }

  public void incrementStatistic(Statistic statistic, EntityType entityType)
      throws IllegalArgumentException {

  }

  public void incrementStatistic(Statistic statistic, EntityType entityType, int amount)
      throws IllegalArgumentException {

  }

  public void playSound(Location location, Sound sound, SoundCategory category, float volume,
      float pitch) {

  }

  public void playSound(Location location, String sound, SoundCategory category, float volume,
      float pitch) {

  }

  public void removeAchievement(Achievement achievement) {

  }

  public void resetTitle() {

  }

  public void sendBlockChange(Location loc, BlockData block) {

  }

  public void sendSignChange(Location loc, String[] lines) throws IllegalArgumentException {

  }

  public void sendTitle(String title, String subtitle) {

  }

  public void sendTitle(String title, String subtitle, int fadeIn, int stay, int fadeOut) {

  }

  public void setPlayerListFooter(String footer) {

  }

  public void setPlayerListHeader(String header) {

  }

  public void setPlayerListHeaderFooter(String header, String footer) {

  }

  public void setResourcePack(String url, byte[] hash) {

  }

  public void setSpectatorTarget(Entity entity) {

  }

  public void setStatistic(Statistic statistic, int newValue) throws IllegalArgumentException {

  }

  public void setStatistic(Statistic statistic, Material material, int newValue)
      throws IllegalArgumentException {

  }

  public void setStatistic(Statistic statistic, EntityType entityType, int newValue) {

  }

  public void showPlayer(Plugin plugin, Player player) {

  }

  public void spawnParticle(Particle particle, Location location, int count) {

  }

  public <T> void spawnParticle(Particle particle, Location location, int count, T data) {

  }

  public void spawnParticle(Particle particle, double x, double y, double z, int count) {

  }

  public <T> void spawnParticle(Particle particle, double x, double y, double z, int count,
      T data) {

  }

  public void spawnParticle(Particle particle, Location location, int count, double offsetX,
      double offsetY, double offsetZ) {

  }

  public <T> void spawnParticle(Particle particle, Location location, int count, double offsetX,
      double offsetY, double offsetZ, T data) {

  }

  public void spawnParticle(Particle particle, Location location, int count, double offsetX,
      double offsetY, double offsetZ, double extra) {

  }

  public void spawnParticle(Particle particle, double x, double y, double z, int count,
      double offsetX, double offsetY, double offsetZ) {

  }

  public <T> void spawnParticle(Particle particle, Location location, int count, double offsetX,
      double offsetY, double offsetZ, double extra, T data) {

  }

  public <T> void spawnParticle(Particle particle, double x, double y, double z, int count,
      double offsetX, double offsetY, double offsetZ, T data) {

  }

  public void spawnParticle(Particle particle, double x, double y, double z, int count,
      double offsetX, double offsetY, double offsetZ, double extra) {

  }

  public <T> void spawnParticle(Particle particle, double x, double y, double z, int count,
      double offsetX, double offsetY, double offsetZ, double extra, T data) {

  }

  public Spigot spigot() {
    return null;
  }

  public void stopSound(Sound sound) {

  }

  public void stopSound(String sound) {

  }

  public void stopSound(Sound sound, SoundCategory category) {

  }

  public void stopSound(String sound, SoundCategory category) {

  }

  public void updateCommands() {

  }


}
