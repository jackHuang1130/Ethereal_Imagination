package cc.jack4444.dreamutils;

import com.loohp.interactivechat.objectholders.ICPlayerFactory;
import com.loohp.interactivechat.objectholders.OfflineICPlayer;
import com.loohp.interactivechatdiscordsrvaddon.graphics.ImageGeneration;
import com.loohp.interactivechatdiscordsrvaddon.graphics.ImageUtils;
import com.loohp.interactivechatdiscordsrvaddon.objectholders.ToolTipComponent;
import com.loohp.interactivechatdiscordsrvaddon.utils.DiscordItemStackUtils;
import github.scarsz.discordsrv.dependencies.jda.api.EmbedBuilder;
import github.scarsz.discordsrv.util.DiscordUtil;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import studio.trc.bukkit.globalmarketplus.api.event.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

public class DiscordHandler {

    public static void MerchandiseSellLogSender(Player player, MerchandiseSellEvent event) throws Exception {
        //getConfig
        File file = new File(DreamUtils.DreamUtils.getDataFolder(), "/config.yml");
        FileConfiguration Config = YamlConfiguration.loadConfiguration(file);
        //load config values
        Long logChannelId = Config.getLong("MerchandiseSellLog.DiscordChannelId.LogChannel");
        Long serverChannelId = Config.getLong("MerchandiseSellLog.DiscordChannelId.ServerChannel");
        String EmbedTitle = Config.getString("MerchandiseSellLog.EmbedTitle");
        String EmbedHexColor = Config.getString("MerchandiseSellLog.EmbedColor");
        String EmbedDescription = Config.getString("MerchandiseSellLog.EmbedDescription");
        String EmbedDescriptionCustomName = Config.getString("MerchandiseSellLog.EmbedDescriptionWithCustomId");
        String iconURL = Config.getString("Utils.iconURL");
        String Footer = Config.getString("Utils.Footer");
        String Author = Config.getString("Utils.Author");
        Color EmbedDecodedColor = Color.decode(EmbedHexColor);

        //build Embed

        EmbedBuilder Embed = new EmbedBuilder();

        Embed.setAuthor(Author, null, "https://www.mc-heads.net/head/" + player.getUniqueId() + "/100/.png");
        Embed.setTitle(EmbedTitle);
        Embed.setColor(EmbedDecodedColor);
        Embed.setFooter(Footer, iconURL);
        Embed.setThumbnail("attachment://image.png");
        Embed.setImage("attachment://tooltip.png");

        if(event.getItem().getItemMeta().hasDisplayName() != true){
            Embed.setDescription(DiscordMessageHandler.ProcessMerchandiseSellMsg(EmbedDescription, event));
        } else {
            Embed.setDescription(DiscordMessageHandler.ProcessMerchandiseSellMsg(EmbedDescriptionCustomName, event));
        }

        //Generate Item Image

        OfflineICPlayer OICP = ICPlayerFactory.getOfflineICPlayer(player.getUniqueId());
        // ItemStack image
        BufferedImage image = ImageGeneration.getItemStackImage(event.getItem(), OICP, 64);
        // Tooltip image
        DiscordItemStackUtils.DiscordToolTip discordToolTip = DiscordItemStackUtils.getToolTip(event.getItem(), OICP);
        List<ToolTipComponent<?>> toolTipComponents = discordToolTip.getComponents();
        BufferedImage tooltip = ImageGeneration.getToolTipImage(toolTipComponents);

        //Send message
        DiscordUtil.getJda().getTextChannelById(logChannelId).sendMessage(Embed.build()).addFile(ImageUtils.toArray(image), "image.png").addFile(ImageUtils.toArray(tooltip), "tooltip.png").queue();
        if(serverChannelId > 0){
            DiscordUtil.getJda().getTextChannelById(serverChannelId).sendMessage(Embed.build()).addFile(ImageUtils.toArray(image), "image.png").addFile(ImageUtils.toArray(tooltip), "tooltip.png").queue();
        }


    }
    public static void MerchandiseSellSuccessLogSender(Player player, TransactionResultEvent event) throws Exception {
        //getConfig
        File file = new File(DreamUtils.DreamUtils.getDataFolder(), "/config.yml");
        FileConfiguration Config = YamlConfiguration.loadConfiguration(file);
        //load config values
        Long logChannelId = Config.getLong("MerchandiseSellSuccessLog.DiscordChannelId.LogChannel");
        Long serverChannelId = Config.getLong("MerchandiseSellSuccessLog.DiscordChannelId.ServerChannel");
        String EmbedTitle = Config.getString("MerchandiseSellSuccessLog.EmbedTitle");
        String EmbedHexColor = Config.getString("MerchandiseSellSuccessLog.EmbedColor");
        String EmbedDescription = Config.getString("MerchandiseSellSuccessLog.EmbedDescription");
        String EmbedDescriptionCustomName = Config.getString("MerchandiseSellSuccessLog.EmbedDescriptionWithCustomId");
        String iconURL = Config.getString("Utils.iconURL");
        String Footer = Config.getString("Utils.Footer");
        String Author = Config.getString("Utils.Author");
        Color EmbedDecodedColor = Color.decode(EmbedHexColor);

        //build Embed

        EmbedBuilder Embed = new EmbedBuilder();

        Embed.setAuthor(Author, null, "https://www.mc-heads.net/head/" + player.getUniqueId() + "/100/.png");
        Embed.setTitle(EmbedTitle);
        Embed.setColor(EmbedDecodedColor);
        Embed.setFooter(Footer, iconURL);
        Embed.setThumbnail("attachment://image.png");
        Embed.setImage("attachment://tooltip.png");

        if(event.getResult().getItem().getItemMeta().hasDisplayName() != true){
            Embed.setDescription(DiscordMessageHandler.ProcessMerchandiseSellSuccessMsg(EmbedDescription, event));
        } else {
            Embed.setDescription(DiscordMessageHandler.ProcessMerchandiseSellSuccessMsg(EmbedDescriptionCustomName, event));
        }

        //Generate Item Image

        OfflineICPlayer OICP = ICPlayerFactory.getOfflineICPlayer(player.getUniqueId());
        // ItemStack image
        BufferedImage image = ImageGeneration.getItemStackImage(event.getResult().getItem(), OICP, 64);
        // Tooltip image
        DiscordItemStackUtils.DiscordToolTip discordToolTip = DiscordItemStackUtils.getToolTip(event.getResult().getItem(), OICP);
        List<ToolTipComponent<?>> toolTipComponents = discordToolTip.getComponents();
        BufferedImage tooltip = ImageGeneration.getToolTipImage(toolTipComponents);

        //Send message
        DiscordUtil.getJda().getTextChannelById(logChannelId).sendMessage(Embed.build()).addFile(ImageUtils.toArray(image), "image.png").addFile(ImageUtils.toArray(tooltip), "tooltip.png").queue();
        if(serverChannelId > 0){
            DiscordUtil.getJda().getTextChannelById(serverChannelId).sendMessage(Embed.build()).addFile(ImageUtils.toArray(image), "image.png").addFile(ImageUtils.toArray(tooltip), "tooltip.png").queue();
        }


    }

    public static void MerchandiseBuyLogSender(Player player, MerchandiseBuyEvent event) throws Exception {
        //getConfig
        File file = new File(DreamUtils.DreamUtils.getDataFolder(), "/config.yml");
        FileConfiguration Config = YamlConfiguration.loadConfiguration(file);
        //load config values
        Long logChannelId = Config.getLong("MerchandiseBuyLog.DiscordChannelId.LogChannel");
        Long serverChannelId = Config.getLong("MerchandiseBuyLog.DiscordChannelId.ServerChannel");
        String EmbedTitle = Config.getString("MerchandiseBuyLog.EmbedTitle");
        String EmbedHexColor = Config.getString("MerchandiseBuyLog.EmbedColor");
        String EmbedDescription = Config.getString("MerchandiseBuyLog.EmbedDescription");
        String EmbedDescriptionCustomName = Config.getString("MerchandiseBuyLog.EmbedDescriptionWithCustomId");
        String iconURL = Config.getString("Utils.iconURL");
        String Footer = Config.getString("Utils.Footer");
        String Author = Config.getString("Utils.Author");
        Color EmbedDecodedColor = Color.decode(EmbedHexColor);

        //build Embed

        EmbedBuilder Embed = new EmbedBuilder();

        Embed.setAuthor(Author, null, "https://www.mc-heads.net/head/" + player.getUniqueId() + "/100/.png");
        Embed.setTitle(EmbedTitle);
        Embed.setColor(EmbedDecodedColor);
        Embed.setFooter(Footer, iconURL);
        Embed.setThumbnail("attachment://image.png");
        Embed.setImage("attachment://tooltip.png");

        if(event.getItem().getItemMeta().hasDisplayName() != true){
            Embed.setDescription(DiscordMessageHandler.ProcessMerchandiseBuyMsg(EmbedDescription, event));
        } else {
            Embed.setDescription(DiscordMessageHandler.ProcessMerchandiseBuyMsg(EmbedDescriptionCustomName, event));
        }

        //Generate Item Image

        OfflineICPlayer OICP = ICPlayerFactory.getOfflineICPlayer(player.getUniqueId());
        // ItemStack image
        BufferedImage image = ImageGeneration.getItemStackImage(event.getItem(), OICP, 64);
        // Tooltip image
        DiscordItemStackUtils.DiscordToolTip discordToolTip = DiscordItemStackUtils.getToolTip(event.getItem(), OICP);
        List<ToolTipComponent<?>> toolTipComponents = discordToolTip.getComponents();
        BufferedImage tooltip = ImageGeneration.getToolTipImage(toolTipComponents);

        //Send message
        DiscordUtil.getJda().getTextChannelById(logChannelId).sendMessage(Embed.build()).addFile(ImageUtils.toArray(image), "image.png").addFile(ImageUtils.toArray(tooltip), "tooltip.png").queue();
        if(serverChannelId > 0){
            DiscordUtil.getJda().getTextChannelById(serverChannelId).sendMessage(Embed.build()).addFile(ImageUtils.toArray(image), "image.png").addFile(ImageUtils.toArray(tooltip), "tooltip.png").queue();
        }

    }

    public static void MerchandiseBuySuccessLogSender(Player player, TransactionResultEvent event) throws Exception {
        //getConfig
        File file = new File(DreamUtils.DreamUtils.getDataFolder(), "/config.yml");
        FileConfiguration Config = YamlConfiguration.loadConfiguration(file);
        //load config values
        Long logChannelId = Config.getLong("MerchandiseBuySuccessLog.DiscordChannelId.LogChannel");
        Long serverChannelId = Config.getLong("MerchandiseBuySuccessLog.DiscordChannelId.ServerChannel");
        String EmbedTitle = Config.getString("MerchandiseBuySuccessLog.EmbedTitle");
        String EmbedHexColor = Config.getString("MerchandiseBuySuccessLog.EmbedColor");
        String EmbedDescription = Config.getString("MerchandiseBuySuccessLog.EmbedDescription");
        String EmbedDescriptionCustomName = Config.getString("MerchandiseBuySuccessLog.EmbedDescriptionWithCustomId");
        String iconURL = Config.getString("Utils.iconURL");
        String Footer = Config.getString("Utils.Footer");
        String Author = Config.getString("Utils.Author");
        Color EmbedDecodedColor = Color.decode(EmbedHexColor);

        //build Embed

        EmbedBuilder Embed = new EmbedBuilder();

        Embed.setAuthor(Author, null, "https://www.mc-heads.net/head/" + player.getUniqueId() + "/100/.png");
        Embed.setTitle(EmbedTitle);
        Embed.setColor(EmbedDecodedColor);
        Embed.setFooter(Footer, iconURL);
        Embed.setThumbnail("attachment://image.png");
        Embed.setImage("attachment://tooltip.png");

        if(event.getResult().getItem().getItemMeta().hasDisplayName() != true){
            Embed.setDescription(DiscordMessageHandler.ProcessMerchandiseBuySuccessMsg(EmbedDescription, event));
        } else {
            Embed.setDescription(DiscordMessageHandler.ProcessMerchandiseBuySuccessMsg(EmbedDescriptionCustomName, event));
        }

        //Generate Item Image

        OfflineICPlayer OICP = ICPlayerFactory.getOfflineICPlayer(player.getUniqueId());
        // ItemStack image
        BufferedImage image = ImageGeneration.getItemStackImage(event.getResult().getItem(), OICP, 64);
        // Tooltip image
        DiscordItemStackUtils.DiscordToolTip discordToolTip = DiscordItemStackUtils.getToolTip(event.getResult().getItem(), OICP);
        List<ToolTipComponent<?>> toolTipComponents = discordToolTip.getComponents();
        BufferedImage tooltip = ImageGeneration.getToolTipImage(toolTipComponents);

        //Send message
        DiscordUtil.getJda().getTextChannelById(logChannelId).sendMessage(Embed.build()).addFile(ImageUtils.toArray(image), "image.png").addFile(ImageUtils.toArray(tooltip), "tooltip.png").queue();
        if(serverChannelId > 0){
            DiscordUtil.getJda().getTextChannelById(serverChannelId).sendMessage(Embed.build()).addFile(ImageUtils.toArray(image), "image.png").addFile(ImageUtils.toArray(tooltip), "tooltip.png").queue();
        }


    }
    public static void AuctionStartLogSender(Player player, AuctionStartEvent event) throws Exception {
        //getConfig
        File file = new File(DreamUtils.DreamUtils.getDataFolder(), "/config.yml");
        FileConfiguration Config = YamlConfiguration.loadConfiguration(file);
        //load config values
        Long logChannelId = Config.getLong("AuctionStartLog.DiscordChannelId.LogChannel");
        Long serverChannelId = Config.getLong("AuctionStartLog.DiscordChannelId.ServerChannel");
        String EmbedTitle = Config.getString("AuctionStartLog.EmbedTitle");
        String EmbedHexColor = Config.getString("AuctionStartLog.EmbedColor");
        String EmbedDescription = Config.getString("AuctionStartLog.EmbedDescription");
        String EmbedDescriptionCustomName = Config.getString("AuctionStartLog.EmbedDescriptionWithCustomId");
        String iconURL = Config.getString("Utils.iconURL");
        String Footer = Config.getString("Utils.Footer");
        String Author = Config.getString("Utils.Author");
        Color EmbedDecodedColor = Color.decode(EmbedHexColor);

        //build Embed

        EmbedBuilder Embed = new EmbedBuilder();

        Embed.setAuthor(Author, null, "https://www.mc-heads.net/head/" + player.getUniqueId() + "/100/.png");
        Embed.setTitle(EmbedTitle);
        Embed.setColor(EmbedDecodedColor);
        Embed.setFooter(Footer, iconURL);
        Embed.setThumbnail("attachment://image.png");
        Embed.setImage("attachment://tooltip.png");

        if(event.getItem().getItemMeta().hasDisplayName() != true){
            Embed.setDescription(DiscordMessageHandler.ProcessAuctionStartMsg(EmbedDescription, event));
        } else {
            Embed.setDescription(DiscordMessageHandler.ProcessAuctionStartMsg(EmbedDescriptionCustomName, event));
        }

        //Generate Item Image

        OfflineICPlayer OICP = ICPlayerFactory.getOfflineICPlayer(player.getUniqueId());
        // ItemStack image
        BufferedImage image = ImageGeneration.getItemStackImage(event.getItem(), OICP, 64);
        // Tooltip image
        DiscordItemStackUtils.DiscordToolTip discordToolTip = DiscordItemStackUtils.getToolTip(event.getItem(), OICP);
        List<ToolTipComponent<?>> toolTipComponents = discordToolTip.getComponents();
        BufferedImage tooltip = ImageGeneration.getToolTipImage(toolTipComponents);

        //Send message
        DiscordUtil.getJda().getTextChannelById(logChannelId).sendMessage(Embed.build()).addFile(ImageUtils.toArray(image), "image.png").addFile(ImageUtils.toArray(tooltip), "tooltip.png").queue();
        if(serverChannelId > 0){
            DiscordUtil.getJda().getTextChannelById(serverChannelId).sendMessage(Embed.build()).addFile(ImageUtils.toArray(image), "image.png").addFile(ImageUtils.toArray(tooltip), "tooltip.png").queue();
        }

    }

    public static void AuctionEventLogSender(Player player, AuctionBidEvent event) throws Exception {
        //getConfig
        File file = new File(DreamUtils.DreamUtils.getDataFolder(), "/config.yml");
        FileConfiguration Config = YamlConfiguration.loadConfiguration(file);
        //load config values
        Long logChannelId = Config.getLong("AuctionEventLog.DiscordChannelId.LogChannel");
        Long serverChannelId = Config.getLong("AuctionEventLog.DiscordChannelId.ServerChannel");
        String EmbedTitle = Config.getString("AuctionEventLog.EmbedTitle");
        String EmbedHexColor = Config.getString("AuctionEventLog.EmbedColor");
        String EmbedDescription = Config.getString("AuctionEventLog.EmbedDescription");
        String EmbedDescriptionCustomName = Config.getString("AuctionEventLog.EmbedDescriptionWithCustomId");
        String iconURL = Config.getString("Utils.iconURL");
        String Footer = Config.getString("Utils.Footer");
        String Author = Config.getString("Utils.Author");
        Color EmbedDecodedColor = Color.decode(EmbedHexColor);

        //build Embed

        EmbedBuilder Embed = new EmbedBuilder();

        Embed.setAuthor(Author, null, "https://www.mc-heads.net/head/" + player.getUniqueId() + "/100/.png");
        Embed.setTitle(EmbedTitle);
        Embed.setColor(EmbedDecodedColor);
        Embed.setFooter(Footer, iconURL);
        Embed.setThumbnail("attachment://image.png");
        Embed.setImage("attachment://tooltip.png");

        if(event.getAuction().getItem().getItemMeta().hasDisplayName() != true){
            Embed.setDescription(DiscordMessageHandler.ProcessAuctionEventMsg(EmbedDescription, event));
        } else {
            Embed.setDescription(DiscordMessageHandler.ProcessAuctionEventMsg(EmbedDescriptionCustomName, event));
        }

        //Generate Item Image

        OfflineICPlayer OICP = ICPlayerFactory.getOfflineICPlayer(player.getUniqueId());
        // ItemStack image
        BufferedImage image = ImageGeneration.getItemStackImage(event.getAuction().getItem(), OICP, 64);
        // Tooltip image
        DiscordItemStackUtils.DiscordToolTip discordToolTip = DiscordItemStackUtils.getToolTip(event.getAuction().getItem(), OICP);
        List<ToolTipComponent<?>> toolTipComponents = discordToolTip.getComponents();
        BufferedImage tooltip = ImageGeneration.getToolTipImage(toolTipComponents);

        //Send message
        DiscordUtil.getJda().getTextChannelById(logChannelId).sendMessage(Embed.build()).addFile(ImageUtils.toArray(image), "image.png").addFile(ImageUtils.toArray(tooltip), "tooltip.png").queue();
        if(serverChannelId > 0){
            DiscordUtil.getJda().getTextChannelById(serverChannelId).sendMessage(Embed.build()).addFile(ImageUtils.toArray(image), "image.png").addFile(ImageUtils.toArray(tooltip), "tooltip.png").queue();
        }

    }

    public static void AuctionEndFailedLogSender(String Bidder, String itemId, String itemDisplayName){
        //getConfig
        File file = new File(DreamUtils.DreamUtils.getDataFolder(), "/config.yml");
        FileConfiguration Config = YamlConfiguration.loadConfiguration(file);
        //load config values
        Long logChannelId = Config.getLong("AuctionEndFailedLog.DiscordChannelId.LogChannel");
        Long serverChannelId = Config.getLong("AuctionEndFailedLog.DiscordChannelId.ServerChannel");
        String EmbedTitle = Config.getString("AuctionEndFailedLog.EmbedTitle");
        String EmbedHexColor = Config.getString("AuctionEndFailedLog.EmbedColor");
        Color EmbedDecodedColor = Color.decode(EmbedHexColor);

        String iconURL = "https://images-ext-2.discordapp.net/external/AuUUQgcieuKl4ZeY4I56Ydhvep_1ear5yc1hCktfKsM/%3Fsize%3D2048/https/cdn.discordapp.com/icons/232865546868228106/a_ab18bcc7cb6b85bf5e040d7a7865ea73.gif?width=559&height=559";

        //Build Embed
        EmbedBuilder AuctionEmbed = new EmbedBuilder();

        AuctionEmbed.setTitle(EmbedTitle);
        //MerchandiseEmbed.setDescription("> 玩家 **`" + player + "`** 以 $" + price + " 發布了 **`" + itemId + "`**\n\n"+"上架時間: <t:"+uploadTime+">");
        AuctionEmbed.setFooter("DreamCrafter 築夢物語 ⋄ 技術開發", iconURL);

        if(itemDisplayName != null){
            AuctionEmbed.setDescription("> 玩家 **`" + Bidder + "`** 於商城拍賣的物品 **`" + itemId + "`**" + " 自定義名字: " + itemDisplayName + "\n" + "> 因無人參與競標而下架");
        }else{
            AuctionEmbed.setDescription("> 玩家 **`" + Bidder + "`** 於商城拍賣的物品 **`" + itemId + "`**" + "\n" + "> 因無人參與競標而下架");
        }

        //MerchandiseEmbed.setDescription("上架時間: <t:"+uploadTime+">");
        AuctionEmbed.setColor(EmbedDecodedColor);


        //Send Message
        DiscordUtil.getJda().getTextChannelById(logChannelId).sendMessage(AuctionEmbed.build()).queue();
        if(serverChannelId > 0) {
            DiscordUtil.getJda().getTextChannelById(serverChannelId).sendMessage(AuctionEmbed.build()).queue();
        }
    }
/*
    public static void AuctionEndNoBidderLogSender(String Bidder, String itemAmount, String itemId, String itemDisplayName){
        //getConfig
        File file = new File(DreamUtils.DreamUtils.getDataFolder(), "/config.yml");
        FileConfiguration Config = YamlConfiguration.loadConfiguration(file);
        //load config values
        Long logChannelId = Config.getLong("AuctionEndNoBidderLog.DiscordChannelId.LogChannel");
        Long serverChannelId = Config.getLong("AuctionEndNoBidderLog.DiscordChannelId.ServerChannel");
        String EmbedTitle = Config.getString("AuctionEndNoBidderLog.EmbedTitle");
        String EmbedHexColor = Config.getString("AuctionEndNoBidderLog.EmbedColor");
        Color EmbedDecodedColor = Color.decode(EmbedHexColor);

        String iconURL = "https://images-ext-2.discordapp.net/external/AuUUQgcieuKl4ZeY4I56Ydhvep_1ear5yc1hCktfKsM/%3Fsize%3D2048/https/cdn.discordapp.com/icons/232865546868228106/a_ab18bcc7cb6b85bf5e040d7a7865ea73.gif?width=559&height=559";

        //Build Embed
        EmbedBuilder AuctionEmbed = new EmbedBuilder();

        AuctionEmbed.setTitle(EmbedTitle);
        //MerchandiseEmbed.setDescription("> 玩家 **`" + player + "`** 以 $" + price + " 發布了 **`" + itemId + "`**\n\n"+"上架時間: <t:"+uploadTime+">");
        AuctionEmbed.setFooter("DreamCrafter 築夢物語 ⋄ 技術開發", iconURL);

        if(itemDisplayName != null){
            AuctionEmbed.setDescription("> 玩家 **`" + Bidder + "`** 主動下架了其於商城競標的 "+ itemAmount + " 個 **`" + itemId + "`**" + "自定義名字: "+itemDisplayName);

        }else{
            AuctionEmbed.setDescription("> 玩家 **`" + Bidder + "`** 主動下架了其於商城競標的 "+ itemAmount + " 個 **`" + itemId + "`**" );

        }

        //MerchandiseEmbed.setDescription("上架時間: <t:"+uploadTime+">");
        AuctionEmbed.setColor(EmbedDecodedColor);


        //Send Message
        DiscordUtil.getJda().getTextChannelById(logChannelId).sendMessage(AuctionEmbed.build()).queue();
        if(serverChannelId > 0) {
            DiscordUtil.getJda().getTextChannelById(serverChannelId).sendMessage(AuctionEmbed.build()).queue();
        }
    }
*/
    public static void AuctionEndSuccessLogSender(Player player, AuctionEndEvent event) throws Exception {
        //getConfig
        File file = new File(DreamUtils.DreamUtils.getDataFolder(), "/config.yml");
        FileConfiguration Config = YamlConfiguration.loadConfiguration(file);
        //load config values
        Long logChannelId = Config.getLong("AuctionEndSuccessLog.DiscordChannelId.LogChannel");
        Long serverChannelId = Config.getLong("AuctionEndSuccessLog.DiscordChannelId.ServerChannel");
        String EmbedTitle = Config.getString("AuctionEndSuccessLog.EmbedTitle");
        String EmbedHexColor = Config.getString("AuctionEndSuccessLog.EmbedColor");
        String EmbedDescription = Config.getString("AuctionEndSuccessLog.EmbedDescription");
        String EmbedDescriptionCustomName = Config.getString("AuctionEndSuccessLog.EmbedDescriptionWithCustomId");
        String iconURL = Config.getString("Utils.iconURL");
        String Footer = Config.getString("Utils.Footer");
        String Author = Config.getString("Utils.Author");
        Color EmbedDecodedColor = Color.decode(EmbedHexColor);

        //build Embed

        EmbedBuilder Embed = new EmbedBuilder();

        Embed.setAuthor(Author, null, "https://www.mc-heads.net/head/" + player.getUniqueId() + "/100/.png");
        Embed.setTitle(EmbedTitle);
        Embed.setColor(EmbedDecodedColor);
        Embed.setFooter(Footer, iconURL);
        Embed.setThumbnail("attachment://image.png");
        Embed.setImage("attachment://tooltip.png");

        if(event.getAuctionResult().getItem().getItemMeta().hasDisplayName() != true){
            Embed.setDescription(DiscordMessageHandler.ProcessAuctionEndSuccessMsg(EmbedDescription, event));
        } else {
            Embed.setDescription(DiscordMessageHandler.ProcessAuctionEndSuccessMsg(EmbedDescriptionCustomName, event));
        }

        //Generate Item Image

        OfflineICPlayer OICP = ICPlayerFactory.getOfflineICPlayer(player.getUniqueId());
        // ItemStack image
        BufferedImage image = ImageGeneration.getItemStackImage(event.getAuctionResult().getItem(), OICP, 64);
        // Tooltip image
        DiscordItemStackUtils.DiscordToolTip discordToolTip = DiscordItemStackUtils.getToolTip(event.getAuctionResult().getItem(), OICP);
        List<ToolTipComponent<?>> toolTipComponents = discordToolTip.getComponents();
        BufferedImage tooltip = ImageGeneration.getToolTipImage(toolTipComponents);

        //Send message
        DiscordUtil.getJda().getTextChannelById(logChannelId).sendMessage(Embed.build()).addFile(ImageUtils.toArray(image), "image.png").addFile(ImageUtils.toArray(tooltip), "tooltip.png").queue();
        if(serverChannelId > 0){
            DiscordUtil.getJda().getTextChannelById(serverChannelId).sendMessage(Embed.build()).addFile(ImageUtils.toArray(image), "image.png").addFile(ImageUtils.toArray(tooltip), "tooltip.png").queue();
        }
    }
    public static void DiscordMessageSender() {


        EmbedBuilder embed = new EmbedBuilder();

        embed.setTitle("測試測試Title", null);
        embed.setDescription("Description測試");

        DiscordUtil.getJda().getTextChannelById("961303765191884862").sendMessage(embed.build()).queue();
        DiscordUtil.getJda().getTextChannelById("961303765191884862").sendMessage("123").queue();
        //return;

    }

}
