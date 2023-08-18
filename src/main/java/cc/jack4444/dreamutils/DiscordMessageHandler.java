package cc.jack4444.dreamutils;

import com.loohp.interactivechat.utils.LanguageUtils;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import studio.trc.bukkit.globalmarketplus.api.event.*;

public class DiscordMessageHandler {
    public static String ProcessMerchandiseSellMsg(String message, MerchandiseSellEvent event){
        String msg;
        if(message.contains("%item_displayName%")){
            msg = message.replace("%item_displayName%", PlainTextComponentSerializer.plainText().serialize(event.getItem().getItemMeta().displayName()));
        } else{
            msg = message;
        }

        return msg
                .replace("%player_name%", event.getPlayer().getName())
                .replace("%price%", String.valueOf(event.getPrice()))
                .replace("%item_amount%", String.valueOf(event.getItem().getAmount()))
                .replace("%item_name%", LanguageUtils.getTranslation(event.getItem().translationKey(), "zh_tw"))
                .replace("%timestamp%", String.valueOf(event.getUploadTime()/1000));

    }

    public static String ProcessMerchandiseSellSuccessMsg(String message, TransactionResultEvent event){
        String msg;
        if(message.contains("%item_displayName%")){
            msg = message.replace("%item_displayName%", PlainTextComponentSerializer.plainText().serialize(event.getResult().getItem().getItemMeta().displayName()));
        } else{
            msg = message;
        }

        return msg
                .replace("%seller%", event.getMerchandise().getOwnerName())
                .replace("%price%", String.valueOf(event.getResult().getPrice()))
                .replace("%buyer%", event.getResult().getTrader().getPlayerName())
                .replace("%item_amount%", String.valueOf(event.getResult().getItem().getAmount()))
                .replace("%item_name%", LanguageUtils.getTranslation(event.getResult().getItem().translationKey(), "zh_tw"));
    }

    public static String ProcessMerchandiseBuyMsg(String message, MerchandiseBuyEvent event){
        String msg;
        if(message.contains("%item_displayName%")){
            msg = message.replace("%item_displayName%", PlainTextComponentSerializer.plainText().serialize(event.getItem().getItemMeta().displayName()));
        } else{
            msg = message;
        }

        return msg
                .replace("%player_name%", event.getPlayer().getName())
                .replace("%price%", String.valueOf(event.getDeposit()))
                .replace("%item_amount%", String.valueOf(event.getItem().getAmount()))
                .replace("%item_name%", LanguageUtils.getTranslation(event.getItem().translationKey(), "zh_tw"))
                .replace("%timestamp%", String.valueOf(event.getUploadTime()/1000));
    }

    public static String ProcessMerchandiseBuySuccessMsg(String message, TransactionResultEvent event){
        String msg;
        if(message.contains("%item_displayName%")){
            msg = message.replace("%item_displayName%", PlainTextComponentSerializer.plainText().serialize(event.getResult().getItem().getItemMeta().displayName()));
        } else{
            msg = message;
        }

        return msg
                .replace("%buyer%", event.getMerchandise().getOwnerName())
                .replace("%price%", String.valueOf(event.getResult().getPrice()))
                .replace("%seller%", event.getResult().getTrader().getPlayerName())
                .replace("%item_amount%", String.valueOf(event.getResult().getItem().getAmount()))
                .replace("%item_name%", LanguageUtils.getTranslation(event.getResult().getItem().translationKey(), "zh_tw"));
    }

    public static String ProcessAuctionStartMsg(String message, AuctionStartEvent event){
        String msg;
        if(message.contains("%item_displayName%")){
            msg = message.replace("%item_displayName%", PlainTextComponentSerializer.plainText().serialize(event.getItem().getItemMeta().displayName()));
        } else{
            msg = message;
        }
        return msg
                .replace("%player_name%", event.getPlayer().getName())
                .replace("%price%", String.valueOf(event.getPrice()))
                .replace("%item_amount%", String.valueOf(event.getItem().getAmount()))
                .replace("%item_name%", LanguageUtils.getTranslation(event.getItem().translationKey(), "zh_tw"))
                .replace("%uploadTime%", String.valueOf(event.getUploadTime()/1000))
                .replace("%expiredTime%", String.valueOf(event.getExpireTime()/1000));

    }

    public static String ProcessAuctionEventMsg(String message, AuctionBidEvent event){
        String msg;
        if(message.contains("%item_displayName%")){
            msg = message.replace("%item_displayName%", PlainTextComponentSerializer.plainText().serialize(event.getAuction().getItem().getItemMeta().displayName()));
        } else{
            msg = message;
        }

        return msg
                .replace("%bid_owner%", event.getNewTopBidder().getName())
                .replace("%newPrice%", String.valueOf(event.getNewBiddingPrice()))
                .replace("%newBidder%", event.getNewTopBidder().getName())
                .replace("%item_amount%", String.valueOf(event.getAuction().getItem().getAmount()))
                .replace("%item_name%", LanguageUtils.getTranslation(event.getAuction().getItem().translationKey(), "zh_tw"));
    }

    public static String ProcessAuctionEndSuccessMsg(String message, AuctionEndEvent event){
        String msg;
        if(message.contains("%item_displayName%")){
            msg = message.replace("%item_displayName%", PlainTextComponentSerializer.plainText().serialize(event.getAuctionResult().getItem().getItemMeta().displayName()));
        } else{
            msg = message;
        }

        return msg
                .replace("%bid_owner%", event.getAuctionResult().getAuction().getOwnerName())
                .replace("%Price%", String.valueOf(event.getAuctionResult().getPrice()))
                .replace("%Buyer%", event.getAuctionResult().getTopBidder().getPlayerName())
                .replace("%item_amount%", String.valueOf(event.getAuctionResult().getItem().getAmount()))
                .replace("%item_name%", LanguageUtils.getTranslation(event.getAuctionResult().getItem().translationKey(), "zh_tw"));
    }
}
