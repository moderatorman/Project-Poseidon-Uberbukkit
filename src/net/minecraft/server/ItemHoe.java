package net.minecraft.server;

// CraftBukkit start
import org.bukkit.craftbukkit.block.CraftBlockState;
import org.bukkit.craftbukkit.event.CraftEventFactory;
import org.bukkit.event.block.BlockPlaceEvent;
// CraftBukkit end

import com.legacyminecraft.poseidon.PoseidonConfig;

public class ItemHoe extends Item {

    public ItemHoe(int i, EnumToolMaterial enumtoolmaterial) {
        super(i);
        this.maxStackSize = 1;
        this.d(enumtoolmaterial.a());
    }

    public boolean a(ItemStack itemstack, EntityHuman entityhuman, World world, int i, int j, int k, int l) {
        int i1 = world.getTypeId(i, j, k);
        int j1 = world.getTypeId(i, j + 1, k);

        if ((l == 0 || j1 != 0 || i1 != Block.GRASS.id) && i1 != Block.DIRT.id) {
            return false;
        } else {
            Block block = Block.SOIL;

            world.makeSound((double) ((float) i + 0.5F), (double) ((float) j + 0.5F), (double) ((float) k + 0.5F), block.stepSound.getName(), (block.stepSound.getVolume1() + 1.0F) / 2.0F, block.stepSound.getVolume2() * 0.8F);
            if (world.isStatic) {
                return true;
            } else {
                CraftBlockState blockState = CraftBlockState.getBlockState(world, i, j, k); // CraftBukkit

                world.setTypeId(i, j, k, block.id);

                // CraftBukkit start - Hoes - blockface -1 for 'SELF'
                BlockPlaceEvent event = CraftEventFactory.callBlockPlaceEvent(world, entityhuman, blockState, i, j, k, block);

                if (event.isCancelled() || !event.canBuild()) {
                    event.getBlockPlaced().setTypeId(blockState.getTypeId());
                    return false;
                }
                // CraftBukkit end

                itemstack.damage(1, entityhuman);

                // uberbukkit
                if (PoseidonConfig.getInstance().getBoolean("version.mechanics.tile_grass_drop_seeds", false)) {
                    if (world.random.nextInt(8) == 0 && i1 == Block.GRASS.id) {
                        byte b0 = 1;

                        for (j1 = 0; j1 < b0; ++j1) {
                            float f = 0.7F;
                            float f1 = world.random.nextFloat() * f + (1.0F - f) * 0.5F;
                            float f2 = 1.2F;
                            float f3 = world.random.nextFloat() * f + (1.0F - f) * 0.5F;
                            EntityItem entityitem = new EntityItem(world, (double) ((float) i + f1), (double) ((float) j + f2), (double) ((float) k + f3), new ItemStack(Item.SEEDS));

                            entityitem.pickupDelay = 10;
                            world.addEntity(entityitem);
                        }
                    }
                }
                return true;
            }
        }
    }
}
