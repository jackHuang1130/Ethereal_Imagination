package cc.jack4444.dreamutils.Listener;

import cc.jack4444.dreamutils.DiscordHandler;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import studio.trc.bukkit.globalmarketplus.api.event.*;

public class GMPListener implements Listener {

    @EventHandler
    public void onMerchandiseSell(MerchandiseSellEvent event) throws Exception {
        //處理訊息
        DiscordHandler.MerchandiseSellLogSender(event.getPlayer(), event);
    }

    @EventHandler
    public void onMerchandiseBuy(MerchandiseBuyEvent event) throws Exception {
        //處理訊息
        DiscordHandler.MerchandiseBuyLogSender(event.getPlayer(), event);
    }

    @EventHandler
    public void onAuctionStartEvent(AuctionStartEvent event) throws Exception {
        DiscordHandler.AuctionStartLogSender(event.getPlayer(), event);
    }


    @EventHandler
    public void onAuctionEndEvent(AuctionEndEvent event) throws Exception {

        String Result = String.valueOf(event.getAuctionResult().getResultType());

        //Bukkit.getLogger().info(Result);

/* API運作不正確。 我不知道他在幹嘛==
        //主動下架物品。結束競標。
        if(Result.equals("NO_BIDDER")){
            String player = event.getAuctionResult().getAuction().getPlayer().getName();
            ItemStack item = event.getAuctionResult().getItem();

            String itemDisplayName;

            if(item.getItemMeta().hasDisplayName()){
                itemDisplayName = PlainTextComponentSerializer.plainText().serialize(item.getItemMeta().displayName());
            } else {
                itemDisplayName = null;
            }
            String itemString = String.valueOf(item.getType());
            //物品數量
            String itemAmount = String.valueOf(item.getAmount());

            DiscordHandler.AuctionEndNoBidderLogSender(player, itemAmount, itemString, itemDisplayName);


        }
*/
        //競標成功。物品被拍出
        if(Result.equals("SUCCESSFUL")){
            DiscordHandler.AuctionEndSuccessLogSender(event.getAuctionResult().getAuction().getPlayer(), event);

        }

    }

    @EventHandler
    public void onAuctionEvent(AuctionBidEvent event) throws Exception {
        DiscordHandler.AuctionEventLogSender(event.getAuction().getPlayer(), event);
    }

    @EventHandler
    public void onTransactionEventEvent(TransactionEvent event){
       String type = String.valueOf(event.getMerchandise().getMerchandiseType());
       //Bukkit.getLogger().info("TransactionEvent RESULT EVENT: "+type);
    }
    @EventHandler
    public void onTransactionResultEvent(TransactionResultEvent event) throws Exception {
        String type = String.valueOf(event.getResult().getMerchandiseType());
        String Result = String.valueOf(event.getResult().getResultType());

        //Bukkit.getLogger().info("Triggered Result Event.");
        //Bukkit.getLogger().info("Transaction RESULT EVENT: "+type +"("+Result+")");

        if(type.equals("SELLING") && Result.equals("SUCCESSFUL") ){
            DiscordHandler.MerchandiseSellSuccessLogSender(event.getMerchandise().getPlayer(), event);
        }

        if(type.equals("PURCHASING") && Result.equals("SUCCESSFUL")) {
            DiscordHandler.MerchandiseBuySuccessLogSender(event.getMerchandise().getPlayer(), event);
        }

    }

}

