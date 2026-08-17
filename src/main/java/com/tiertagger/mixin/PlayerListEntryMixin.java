package com.tiertagger.mixin;

import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import com.tiertagger.cache.TierCache;
import com.tiertagger.model.PlayerTierData;
import com.tiertagger.util.TierColorFormatter;

@Mixin(PlayerListEntry.class)
public class PlayerListEntryMixin {

    @Inject(method = "getDisplayName", at = @At("HEAD"), cancellable = true)
    private void injectDisplayName(CallbackInfoReturnable<Text> cir) {
        PlayerListEntry entry = (PlayerListEntry) (Object) this;
        String playerName = entry.getProfile().getName();
        
        PlayerTierData tierData = TierCache.get(playerName);
        if (tierData != null) {
            String highestTier = TierColorFormatter.getTierForGamemode(tierData.getTiers());
            if (highestTier != null) {
                MutableText displayName = (MutableText) entry.getDisplayName();
                if (displayName == null) {
                    displayName = Text.literal(playerName);
                }
                displayName.append(" ");
                displayName.append(TierColorFormatter.formatTierTag(highestTier));
                cir.setReturnValue(displayName);
            }
        }
    }
}
